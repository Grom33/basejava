package ru.javawebinar.basejava.model;


import java.util.Objects;

public class LineTxtBlock extends TxtBlock {
    private String line;

    public LineTxtBlock(String line) {
        Objects.requireNonNull(line);
        this.line = line;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LineTxtBlock that = (LineTxtBlock) o;

        return line.equals(that.line);
    }

    @Override
    public int hashCode() {
        return line.hashCode();
    }

    @Override
    public String toString() {
        return "* '" + line + '\'' +
                "\n";
    }
}
