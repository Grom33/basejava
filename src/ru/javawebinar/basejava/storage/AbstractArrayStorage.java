package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];


    public void eraseStorage() {
        Arrays.fill(storage, 0, size - 1, null);
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public void insertResume(Resume r, int i) {
        if (size >= STORAGE_LIMIT) throw new StorageException("Storage overflow", r.getUuid());
        insertResumeToStorage(r, i);
    }


    @Override
    public void updateResume(Resume r, int i) {

        storage[i] = r;
    }

    @Override
    protected void deleteResume(String uuid) {
        int i = getIndex(uuid);
        if (i >= 0) {
            eraseResume(i);
            storage[size - 1] = null;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    protected Resume getResume(int i) {
        return storage[i];
    }


    protected abstract void eraseResume(int i);

    protected abstract void insertResumeToStorage(Resume r, int i);



}
