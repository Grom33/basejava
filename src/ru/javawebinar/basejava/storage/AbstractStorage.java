package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected int size = 0;

    @Override
    public void clear() {
        if (size <= 0) return;
        eraseStorage();
        size = 0;
    }

    @Override
    public void update(Resume r) {
        if (size <= 0) return;
        int i = getIndex(r.getUuid());
        if (i < 0) return;

        updateResume(r, i);
    }

    @Override
    public void save(Resume r) {

        int i = getIndex(r.getUuid());
        if (i < 0) {
            insertResume(r, i);
        } else {
            throw new ExistStorageException(r.getUuid());
        }

        size++;
    }

    @Override
    public void delete(String uuid) {
        if (size == 0) return;
        deleteResume(uuid);

        size--;
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getResume(index);
    }

    @Override
    public int size() {
        return size;
    }

    protected abstract void updateResume(Resume r, int i);

    protected abstract void deleteResume(String uuid);

    protected abstract void insertResume(Resume r, int i);

    protected abstract Resume getResume(int i);

    protected abstract void eraseStorage();

    protected abstract int getIndex(String uuid);
}
