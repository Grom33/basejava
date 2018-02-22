package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class SqlStorage implements Storage {
    private final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        this.sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.doQuery("DELETE FROM resume");
    }

    @Override
    public void update(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
                    sqlHelper.doQueryWithParams(conn
                            , "UPDATE resume SET full_name=? WHERE uuid=?"
                            , r.getFullName()
                            , r.getUuid());
                    sqlHelper.doQueryWithParams(conn
                            , "DELETE FROM contact c WHERE c.resume_uuid =?"
                            , r.getUuid());
                    insertResumeContacts(r, conn);
                    sqlHelper.doQueryWithParams(conn
                            , "DELETE FROM section s WHERE s.resume_uuid =?"
                            , r.getUuid());
                    insertResumeSection(r, conn);
                    return null;
                }
        );
    }

    @Override
    public void save(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
                    sqlHelper.doQueryWithParams(conn
                            , "INSERT INTO resume (uuid, full_name) VALUES (?,?)"
                            , r.getUuid()
                            , r.getFullName());
                    insertResumeContacts(r, conn);
                    insertResumeSection(r, conn);
                    return null;
                }
        );
    }

    @Override
    public Resume get(String uuid) {
       /* Resume resume = sqlHelper.execute("" +
                        "    SELECT * FROM resume r " +
                        " LEFT JOIN contact c " +
                        "        ON r.uuid = c.resume_uuid " +
                        "     WHERE r.uuid =? ",
                ps -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    Resume r = new Resume(uuid, rs.getString("full_name"));
                    do {
                        if (!(rs.getString("type") == null)) {
                            String value = rs.getString("value");
                            ContactType type = ContactType.valueOf(rs.getString("type"));
                            r.addContact(type, value);
                        }
                    } while (rs.next());
                    return r;
                });

        Map<SectionType, Section> sections = sqlHelper.execute("" +
                        "    SELECT * FROM resume r " +
                        " LEFT JOIN section s " +
                        "        ON r.uuid = s.resume_uuid " +
                        "     WHERE r.uuid =? ",
                ps -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    Map<SectionType, Section> transSects = new HashMap<>();
                    do {
                        if (!(rs.getString("type") == null)) {
                            SectionType sectionType = SectionType.valueOf(rs.getString("type"));
                            String[] sectsValues = rs.getString("value").split("\\n");
                            if (sectsValues.length == 1) {
                                transSects.put(sectionType, new TextSection(sectsValues[0]));
                            } else if (sectsValues.length > 1) {
                                transSects.put(sectionType, new ListSection(sectsValues));
                            }
                        }
                    } while (rs.next());
                    return transSects;
                });

        resume.addSections(sections);
        return resume;*/
        return getResumeWithDiffObject(uuid);
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume r WHERE r.uuid =?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        Map<String, Resume> resumes = sqlHelper.execute("    SELECT * FROM resume r " +
                        "              LEFT JOIN contact c " +
                        "                     ON r.uuid = c.resume_uuid" +
                        "               ORDER BY r.full_name, r.uuid"
                , ps -> {
                    ResultSet rs = ps.executeQuery();
                    Map<String, Resume> transResumes = new LinkedHashMap<>();
                    if (!rs.next()) {
                        throw new NotExistStorageException(null);
                    } else {
                        String previousUUID = rs.getString("uuid");
                        Resume newResume = new Resume(previousUUID, rs.getString("full_name"));

                        do {
                            if (!previousUUID.equals(rs.getString("uuid"))) {
                                transResumes.put(previousUUID, newResume);
                                previousUUID = rs.getString("uuid");
                                newResume = new Resume(previousUUID, rs.getString("full_name"));
                            }
                            if (!(rs.getString("value") == null)) {
                                String value = rs.getString("value");
                                ContactType type = ContactType.valueOf(rs.getString("type"));
                                newResume.addContact(type, value);
                            }
                        } while (rs.next());
                        transResumes.put(previousUUID, newResume);
                    }
                    return transResumes;
                });

        sqlHelper.execute("    SELECT * FROM section"
                , ps -> {
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(null);
                    } else {
                        do {
                            if (!(rs.getString("type") == null)) {
                                SectionType sectionType = SectionType.valueOf(rs.getString("type"));
                                String[] sectsValues = rs.getString("value").split("\\n");
                                if (sectsValues.length == 1) {
                                    resumes.get(rs.getString("resume_uuid"))
                                            .addSection(sectionType, new TextSection(sectsValues[0]));
                                } else if (sectsValues.length > 1) {
                                    resumes.get(rs.getString("resume_uuid"))
                                            .addSection(sectionType, new ListSection(sectsValues));
                                }
                            }
                        } while (rs.next());
                    }
                    return null;
                });
        return new ArrayList<>(resumes.values());
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT COUNT(*) FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    private void insertResumeContacts(Resume r, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void insertResumeSection(Resume r, Connection conn) throws SQLException {
        if (r.getSections().size() == 0) return;
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO section (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<SectionType, Section> e : r.getSections().entrySet()) {

                String value = "";
                switch (e.getKey().name()) {
                    case "PERSONAL":
                    case "OBJECTIVE":
                        value = ((TextSection) e.getValue()).getContent();
                        break;
                    case "ACHIEVEMENT":
                    case "QUALIFICATIONS":
                        value = ((ListSection) e.getValue()).getItems().stream()
                                .collect(Collectors.joining("\n"));
                        break;
                }
                if (!value.equals("")) {
                    ps.setString(1, r.getUuid());
                    ps.setString(2, e.getKey().name());
                    ps.setString(3, value);
                    ps.addBatch();
                }
            }
            ps.executeBatch();
        }
    }

    private Resume getResumeWithDiffObject(String uuid) {
        Resume resume = sqlHelper.execute("" +
                        "    SELECT * FROM resume r " +
                        "     WHERE r.uuid =? ",
                ps -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    Resume r = new Resume(uuid, rs.getString("full_name"));
                    return r;
                });

        sqlHelper.execute("" +
                        "    SELECT * FROM contact c " +
                        "     WHERE c.resume_uuid =? ",
                ps -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        do {
                            if (!(rs.getString("type") == null)) {
                                String value = rs.getString("value");
                                ContactType type = ContactType.valueOf(rs.getString("type"));
                                resume.addContact(type, value);
                            }
                        } while (rs.next());
                    }
                    return null;
                });

        Map<SectionType, Section> sections = sqlHelper.execute("" +
                        "    SELECT * FROM section s " +
                        "     WHERE s.resume_uuid =? ",
                ps -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    Map<SectionType, Section> transSects = new HashMap<>();
                    if (rs.next()) {

                        do {
                            if (!(rs.getString("type") == null)) {
                                SectionType sectionType = SectionType.valueOf(rs.getString("type"));
                                String[] sectsValues = rs.getString("value").split("\\n");
                                if (sectsValues.length == 1) {
                                    transSects.put(sectionType, new TextSection(sectsValues[0]));
                                } else if (sectsValues.length > 1) {
                                    transSects.put(sectionType, new ListSection(sectsValues));
                                }
                            }
                        } while (rs.next());
                    }
                    return transSects;
                });

        resume.addSections(sections);
        return resume;
    }
}
