package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization {
    private final String header;
    private List<SkillsItem> skillsItems;

    Organization(String header, List<SkillsItem> skillsItems) {
        Objects.requireNonNull(header);
        Objects.requireNonNull(skillsItems);
        this.header = header;
        this.skillsItems = skillsItems;
    }

    public Organization(String header, SkillsItem skill) {
        this.header = header;
        this.skillsItems = new ArrayList<>();
        this.skillsItems.add(skill);
    }


    public void addSkill(LocalDate begin, LocalDate end, String title, String desc) {
        skillsItems.add(new SkillsItem(begin, end, title, desc));
    }

    public void addSkill(LocalDate begin, LocalDate end, String title) {
        skillsItems.add(new SkillsItem(begin, end, title));
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization skill = (Organization) o;

        if (!header.equals(skill.header)) return false;
        return skillsItems.equals(skill.skillsItems);
    }

    @Override
    public int hashCode() {
        int result = header.hashCode();
        result = 31 * result + skillsItems.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "header='" + header + '\'' +
                ", skillsItems=" + skillsItems +
                '}';
    }
}
