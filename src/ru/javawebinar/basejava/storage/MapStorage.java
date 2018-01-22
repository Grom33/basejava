package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class MapStorage extends AbstractStorage {
    @Override
    protected void updateResume(Resume r, Object resumeKey) {

    }

    @Override
    protected void deleteResume(Object resumeKey) {

    }

    @Override
    protected void insertResume(Resume r, Object resumeKey) {

    }

    @Override
    protected Resume getResume(Object resumeKey) {
        return null;
    }

    @Override
    protected boolean isExist(Object resumeKey) {
        return false;
    }

    @Override
    protected Object getResumeKey(String uuid) {
        return null;
    }

    @Override
    public void clear() {

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
