package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class OrganizationTxtBlock extends TxtBlock {

    private final List<Organization> organizations;

    public OrganizationTxtBlock(List<Organization> organizations) {
        Objects.requireNonNull(organizations);
        this.organizations = organizations;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationTxtBlock that = (OrganizationTxtBlock) o;

        return organizations.equals(that.organizations);
    }

    @Override
    public int hashCode() {
        return organizations.hashCode();
    }

    @Override
    public String toString() {
        return "OrganizationTxtBlock{" +
                "organizations=" + organizations +
                '}';
    }
}
