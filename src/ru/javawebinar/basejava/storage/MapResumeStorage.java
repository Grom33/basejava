package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {
    protected Map<String, Resume> storage = new HashMap<>();

    @Override
    protected void updateResume(Resume r, Object resumeKey) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void deleteResume(Object resumeKey) {
        storage.remove(((Resume) resumeKey).getUuid());
    }

    @Override
    protected void insertResume(Resume r, Object resumeKey) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected Resume getResume(Object resumeKey) {
        return (Resume) resumeKey;
    }

    @Override
    protected boolean isExist(Object resumeKey) {
        return resumeKey!=null;
    }

    @Override
    protected Resume getResumeKey(String uuid) {
        return storage.get(uuid);
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
