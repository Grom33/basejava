package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {
    private static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        if (size > 0) Arrays.fill(storage, 0, size - 1, null);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Collection<Resume> getCollResume() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    @Override
    public void insertResume(Resume r, Object resumeKey) {
        if (size >= STORAGE_LIMIT) throw new StorageException("Storage overflow", r.getUuid());
        insertResumeToStorage(r, (Integer) resumeKey);
        size++;
    }

    @Override
    public void updateResume(Resume r, Object resumeKey) {
        storage[(Integer) resumeKey] = r;
    }

    @Override
    protected void deleteResume(Object resumeKey) {
        eraseResume((Integer) resumeKey);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected Resume getResume(Object resumeKey) {
        return storage[(Integer) resumeKey];
    }

    @Override
    protected boolean isExist(Object resumeKey) {
        return (Integer) resumeKey >= 0;
    }

    protected abstract void eraseResume(int i);

    protected abstract void insertResumeToStorage(Resume r, int i);


}
