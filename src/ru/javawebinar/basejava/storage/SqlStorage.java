package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.ConnectionFactory;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    public final ConnectionFactory connectionFactory;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = SqlHelper.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        SqlHelper.doQuery(connectionFactory, "DELETE FROM resume");
    }

    @Override
    public void update(Resume r) {
        String sql = "UPDATE resume r SET r.full_name=? WHERE r.uuid=? ";
        SqlHelper.doQuery(connectionFactory, sql, r.getFullName(), r.getUuid());
    }

    @Override
    public void save(Resume r) {
        String sql = "INSERT INTO resume (uuid, full_name) VALUES (?,?)";
        SqlHelper.doQuery(connectionFactory, sql, r.getUuid(), r.getFullName());
    }

    @Override
    public Resume get(String uuid) throws SQLException {
        String sql = "SELECT * FROM resume r WHERE r.uuid =?";
        ResultSet rs = SqlHelper.getResultSet(connectionFactory, sql, uuid);
        if (!rs.next()) {
            throw new NotExistStorageException(uuid);
        }
        return new Resume(uuid, rs.getString("full_name"));
    }

    @Override
    public void delete(String uuid) {
        String sql = "DELETE FROM resume r WHERE r.uuid =?";
        SqlHelper.doQuery(connectionFactory, sql, uuid);
    }

    @Override
    public List<Resume> getAllSorted() throws SQLException {
        List<Resume> resumes = new ArrayList<>();
        String sql = "SELECT * FROM resume";
        ResultSet rs = SqlHelper.getResultSet(connectionFactory, sql);
        if (!rs.next()) {
            throw new NotExistStorageException(null);
        } else {
            while (rs.next()) {
                resumes.add(new Resume(rs.getString("uuid"), rs.getString("full_name")));
            }
        }
        return resumes;
    }

    @Override
    public int size() throws SQLException {
        String sql = "SELECT COUNT(*) FROM resume";
        return SqlHelper.getResultSet(connectionFactory, sql).getInt("COUNT");
    }
}
