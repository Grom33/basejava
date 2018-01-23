package ru.javawebinar.basejava.model;

import java.util.UUID;

/**
 * com.urise.webapp.model.ru.javawebinar.basejava.model.Resume class
 */
public class Resume {
    // Unique identifier
    private final String uuid;
    private String fullName;

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public Resume() {
        this.uuid       = UUID.randomUUID().toString();
        this.fullName   = "Empty";
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public Resume(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;
        return uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public String toString() {
        return uuid+" "+fullName;
    }

}
