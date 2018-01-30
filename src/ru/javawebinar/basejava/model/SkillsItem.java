package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Objects;

public class SkillsItem {
    private final LocalDate beginDate;
    private final LocalDate endDate;
    private final String title;
    private String description;

    public SkillsItem(LocalDate beginDate, LocalDate endDate, String title, String description) {
        Objects.requireNonNull(beginDate);
        Objects.requireNonNull(endDate);
        Objects.requireNonNull(title);
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }

    public SkillsItem(LocalDate beginDate, LocalDate endDate, String title) {
        Objects.requireNonNull(beginDate);
        Objects.requireNonNull(endDate);
        Objects.requireNonNull(title);
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SkillsItem that = (SkillsItem) o;

        if (!beginDate.equals(that.beginDate)) return false;
        if (!endDate.equals(that.endDate)) return false;
        if (!title.equals(that.title)) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = beginDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SkillsItem{" +
                "beginDate='" + beginDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}


