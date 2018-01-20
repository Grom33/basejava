package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    private static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void eraseStorage() {
        if (size>0) Arrays.fill(storage, 0, size - 1, null);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    protected boolean ResumeIsExist(String uuid) {
        return (getIndex(uuid)>=0);
    }

    @Override
    public void insertResume(Resume r) {
        if (size >= STORAGE_LIMIT) throw new StorageException("Storage overflow", r.getUuid());
        int i = getIndex(r.getUuid());
        insertResumeToStorage(r, i);
        size++;
    }

    @Override
    public void updateResume(Resume r) {
        storage[getIndex(r.getUuid())] = r;
    }

    @Override
    protected void deleteResume(String uuid) {
        int i = getIndex(uuid);
        if (i >= 0) {
            eraseResume(i);
            storage[size - 1] = null;
            size--;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    protected Resume getResume(String uuid) {
        return storage[getIndex(uuid)];
    }

    protected abstract void eraseResume(int i);

    protected abstract void insertResumeToStorage(Resume r, int i);



}
