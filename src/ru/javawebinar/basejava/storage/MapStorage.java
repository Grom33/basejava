package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {
    protected Map<String, Resume> storage = new HashMap<>();

    @Override
    protected void updateResume(Resume r, Object resumeKey) {
        storage.put((String) resumeKey, r);
    }

    @Override
    protected void deleteResume(Object resumeKey) {
        storage.remove(resumeKey);
    }

    @Override
    protected void insertResume(Resume r, Object resumeKey) {
        storage.put((String) resumeKey, r);
    }

    @Override
    protected Resume getResume(Object resumeKey) {
        return storage.get(resumeKey);
    }

    @Override
    protected boolean isExist(Object resumeKey) {
        return storage.containsKey(resumeKey);
    }

    @Override
    protected Object getResumeKey(String uuid) {
        return uuid;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public ArrayList<Resume> getCollResume() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }
}
