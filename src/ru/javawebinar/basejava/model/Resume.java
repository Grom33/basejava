package ru.javawebinar.basejava.model;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * com.urise.webapp.model.ru.javawebinar.basejava.model.Resume class
 */
public class Resume {
    // Unique identifier
    private final String uuid;
    private String fullName;

    private final Map<ContactType, String> contactBlock = new EnumMap<>(ContactType.class);
    private final Map<SectionType, TxtBlock> textBlock = new EnumMap<>(SectionType.class);

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(fullName, "Full name is null");
        Objects.requireNonNull(uuid, "UUID is null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getContactBlock(ContactType type) {
        return contactBlock.get(type);
    }

    public TxtBlock getTextBlock(SectionType type) {
        return textBlock.get(type);
    }

    public void addTextBlock(SectionType sectionType, TxtBlock textBlock) {
        this.textBlock.put(sectionType, textBlock);
    }

    public void addContactBlock(ContactType contactType, String contact){
        this.contactBlock.put(contactType, contact);
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) &&
                Objects.equals(fullName, resume.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName);
    }

    @Override
    public String toString() {
        return uuid + " " + fullName;
    }

}
