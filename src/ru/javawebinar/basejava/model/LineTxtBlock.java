package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class LineTxtBlock extends TxtBlock {
    private final List<String> lines;

    public LineTxtBlock(List<String> lines) {
        Objects.requireNonNull(lines, "line of text must not be null");
        this.lines = lines;
    }

    public List<String> getLines() {
        return lines;
    }

    @Override
    public String toString() {
        return "LineTxtBlock{" +
                "lines=" + lines +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineTxtBlock that = (LineTxtBlock) o;
        return lines.equals(that.lines);
    }

    @Override
    public int hashCode() {
        return lines.hashCode();
    }
}
