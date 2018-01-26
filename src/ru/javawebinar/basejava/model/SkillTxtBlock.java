package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class SkillTxtBlock extends TxtBlock {

    private final List<Skills> skills;

    public SkillTxtBlock(List<Skills> skills) {
        Objects.requireNonNull(skills);
        this.skills = skills;
    }

}
