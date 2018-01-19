package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class MapStorage extends AbstractStorage {

    @Override
    protected void deleteResume(String uuid) {

    }

    @Override
    protected void insertResume(Resume r, int i) {

    }

    @Override
    protected Resume getResume(int i) {
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
    public Resume get(String uuid) {
        return null;
    }

    @Override
    protected void updateResume(Resume r, int i) {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

}
