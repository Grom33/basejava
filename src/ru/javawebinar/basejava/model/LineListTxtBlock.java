package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class LineListTxtBlock extends TxtBlock {
    private final List<LineTxtBlock> lines;

    public LineListTxtBlock(List<LineTxtBlock> lines) {
        Objects.requireNonNull(lines, "line of text must not be null");
        this.lines = lines;
    }

    public List<LineTxtBlock> getLines() {
        return lines;
    }

    @Override
    public String toString() {
        return "LineListTxtBlock{" +"\n"+
                "* " + lines +
                "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineListTxtBlock that = (LineListTxtBlock) o;
        return lines.equals(that.lines);
    }

    @Override
    public int hashCode() {
        return lines.hashCode();
    }
}
