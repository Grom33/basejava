package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class MapStorage extends AbstractStorage {

    @Override
    protected void updateResume(Resume r) {

    }

    @Override
    protected boolean ResumeIsExist(String uuid) {
        return false;
    }

    @Override
    protected void deleteResume(String uuid) {

    }

    @Override
    protected void insertResume(Resume r) {

    }

    @Override
    protected Resume getResume(String uuid) {
        return null;
    }

    @Override
    protected void eraseStorage() {

    }

    @Override
    protected int getIndex(String uuid) {
        return 0;
    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return 0;
    }
}
