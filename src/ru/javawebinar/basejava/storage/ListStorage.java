package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void updateResume(Resume r, Object resumeKey) {
        storage.set((Integer) resumeKey, r);
    }

    @Override
    protected void deleteResume(Object resumeKey) {
        storage.remove((int) resumeKey);
    }

    @Override
    protected void insertResume(Resume r, Object resumeKey) {
        storage.add(r);
    }

    @Override
    protected Resume getResume(Object resumeKey) {
        return storage.get((Integer) resumeKey);
    }

    @Override
    public List<Resume> getCollResume() {
        return new ArrayList<>(storage);
    }

    @Override
    protected Integer getResumeKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object resumeKey) {
        return resumeKey != null;
    }
}
