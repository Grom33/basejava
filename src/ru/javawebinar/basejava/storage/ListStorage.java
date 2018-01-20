package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class ListStorage extends AbstractStorage {
    protected ArrayList<Resume> storage = new ArrayList();

    @Override
    protected void eraseStorage() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void updateResume(Resume r) {
        storage.set(getIndex(r.getUuid()), r);
    }

    @Override
    protected void deleteResume(String uuid) {
        storage.remove(getResume(uuid));
    }

    @Override
    protected void insertResume(Resume r) {
        storage.add(r);
    }

    @Override
    protected Resume getResume(String uuid) {
        return storage.get(getIndex(uuid));
    }

    @Override
    public Resume[] getAll() {
        return (Resume[]) storage.toArray();
    }

    @Override
    protected boolean ResumeIsExist(String uuid) {
        return (getIndex(uuid) >= 0);
    }

    @Override
    protected int getIndex(String uuid) {
        for (Resume r : storage) {
            if (Objects.equals(r.getUuid(), uuid)) return storage.indexOf(r);
        }
      return -1;
    }

}
