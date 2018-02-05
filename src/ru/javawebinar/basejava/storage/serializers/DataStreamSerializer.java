package ru.javawebinar.basejava.storage.serializers;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements ResumeSerializer {
    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            Map<SectionType, Section> sections = r.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                String section = entry.getKey().name();
                dos.writeUTF(section);

                // dos.writeUTF(entry.getValue().getClass().getName());
                switch (section) {
                    case "PERSONAL":
                    case "OBJECTIVE":
                        TextSection textSection = (TextSection) entry.getValue();
                        dos.writeUTF(textSection.getContent());
                        break;
                    case "ACHIEVEMENT":
                    case "QUALIFICATIONS":
                        ListSection listSection = (ListSection) entry.getValue();
                        dos.writeUTF(String.valueOf(listSection.getItems().size()));
                        listSection.getItems().forEach(item -> dosWrite(dos, item));
                        break;
                    case "EXPERIENCE":
                    case "EDUCATION":
                        OrganizationSection organizationSection = (OrganizationSection) entry.getValue();
                        List<Organization> organizations = organizationSection.getOrganizations();
                        dos.writeUTF(String.valueOf(organizations.size()));
                        for (Organization org : organizations) {
                            dos.writeUTF(org.getHomePage().getName());
                            dos.writeUTF(org.getHomePage().getUrl());
                            List<Organization.Position> position = org.getPositions();
                            dos.writeUTF(String.valueOf(position.size()));
                            for (Organization.Position pos : position) {
                                dos.writeUTF(pos.getStartDate().toString());
                                dos.writeUTF(pos.getEndDate().toString());
                                dos.writeUTF(pos.getTitle());
                                dos.writeUTF(pos.getDescription());
                            }
                        }
                        break;
                }
            }
        } catch (IOException e) {
            throw new StorageException("", null, e);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int sizeContacts = dis.readInt();

            for (int i = 0; i < sizeContacts; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            int sizeSections = dis.readInt();
            for (int i = 0; i < sizeSections; i++) {
                String section = dis.readUTF();
                SectionType sectionType = SectionType.valueOf(section);
                switch (section) {
                    case "PERSONAL":
                    case "OBJECTIVE":
                        resume.addSection(sectionType, new TextSection(dis.readUTF()));
                        break;
                    case "ACHIEVEMENT":
                    case "QUALIFICATIONS":
                        int itemSize = Integer.valueOf(dis.readUTF());
                        List<String> items = new ArrayList<>();
                        for (int it = 0; it < itemSize; it++) {
                            items.add(dis.readUTF());
                        }
                        resume.addSection(sectionType, new ListSection(items));
                        break;
                    case "EXPERIENCE":
                    case "EDUCATION":
                        int orgSize = Integer.valueOf(dis.readUTF());
                        List<Organization> orgList = new ArrayList<>();
                        for (int ios = 0; ios < orgSize; ios++) {
                            Link link = new Link(dis.readUTF(), dis.readUTF());
                            List<Organization.Position> newpos = new ArrayList<>();
                            int posSize = Integer.valueOf(dis.readUTF());
                            for (int ipos = 0; ipos < posSize; ipos++) {
                                LocalDate sd = LocalDate.parse(dis.readUTF());
                                LocalDate ld = LocalDate.parse(dis.readUTF());
                                newpos.add(new Organization.Position(sd, ld, dis.readUTF(), dis.readUTF()));
                            }
                            orgList.add(new Organization(link, newpos));
                        }
                        resume.addSection(sectionType, new OrganizationSection(orgList));
                        break;
                }
            }
            return resume;
        }
    }

    private void dosWrite(DataOutputStream dos, String data) {
        try {
            dos.writeUTF(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

