package ru.javawebinar.basejava.model;

import java.util.Objects;

public class Skills {
    private final String header;
    private final String beginDate;
    private final String endDate;
    private final String title;
    private final String description;

    public Skills(String header, String beginDate, String endDate, String title, String description) {
        Objects.requireNonNull(beginDate);
        Objects.requireNonNull(endDate);
        Objects.requireNonNull(title);
        Objects.requireNonNull(header);
        this.header = header;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Skills skills = (Skills) o;

        if (!header.equals(skills.header)) return false;
        if (!beginDate.equals(skills.beginDate)) return false;
        if (!endDate.equals(skills.endDate)) return false;
        return title.equals(skills.title);
    }

    @Override
    public int hashCode() {
        int result = header.hashCode();
        result = 31 * result + beginDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + title.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Skills{" +
                "header='" + header + '\'' +
                ", beginDate='" + beginDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
