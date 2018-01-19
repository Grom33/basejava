package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {
    protected ArrayList<Resume> storage = new ArrayList();

    @Override
    protected void updateResume(Resume r, int i) {
        storage.set(i, r);
    }

    @Override
    protected void deleteResume(String uuid) {
        storage.remove(new Resume(uuid));
    }

    @Override
    protected void insertResume(Resume r, int i) {
        storage.add(r);
    }

    @Override
    protected void eraseStorage() {
        storage.clear();
    }

    @Override
    protected Resume getResume(int i) {
        return storage.get(i);
    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    protected int getIndex(String uuid) {
        Resume r = new Resume(uuid);
        return storage.indexOf(r);
    }
}
