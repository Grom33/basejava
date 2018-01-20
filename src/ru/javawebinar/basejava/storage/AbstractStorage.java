package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {


    @Override
    public void clear() {
        eraseStorage();
    }

    @Override
    public void update(Resume r) {
        checkExist(r.getUuid());
        updateResume(r);
    }

    @Override
    public void delete(String uuid) {
        checkExist(uuid);
        deleteResume(uuid);
    }

    @Override
    public void save(Resume r) {
        checkNotExist(r.getUuid());
        insertResume(r);
    }

    @Override
    public Resume get(String uuid) {
        checkExist(uuid);
        return getResume(uuid);
    }

    private void checkExist(String uuid) {
        if (!ResumeIsExist(uuid)) throw new NotExistStorageException(uuid);
    }

    private void checkNotExist(String uuid) {
        if (ResumeIsExist(uuid)) throw new ExistStorageException(uuid);
    }

    protected abstract void updateResume(Resume r);

    protected abstract boolean ResumeIsExist(String uuid);

    protected abstract void deleteResume(String uuid);

    protected abstract void insertResume(Resume r);

    protected abstract Resume getResume(String uuid);

    protected abstract void eraseStorage();

    protected abstract int getIndex(String uuid);
}
