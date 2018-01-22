package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume r) {
        Object resumeKey = getResumeKey(r.getUuid());
        checkExist(resumeKey, r.getUuid());
        updateResume(r, resumeKey);
    }

    @Override
    public void delete(String uuid) {
        Object resumeKey = getResumeKey(uuid);
        checkExist(resumeKey, uuid);
        deleteResume(resumeKey);
    }

    @Override
    public void save(Resume r) {
        Object resumeKey = getResumeKey(r.getUuid());
        checkNotExist(resumeKey, r.getUuid());
        insertResume(r, resumeKey);
    }

    @Override
    public Resume get(String uuid) {
        Object resumeKey = getResumeKey(uuid);
        checkExist(resumeKey, uuid);
        return getResume(resumeKey);
    }

    private void checkExist(Object resumeKey, String uuid) {
        if (!isExist(resumeKey)) throw new NotExistStorageException(uuid);
    }

    private void checkNotExist(Object resumeKey, String uuid) {
        if (isExist(resumeKey)) throw new ExistStorageException(uuid);
    }

    protected abstract void updateResume(Resume r, Object resumeKey);

    protected abstract void deleteResume(Object resumeKey);

    protected abstract void insertResume(Resume r, Object resumeKey);

    protected abstract Resume getResume(Object resumeKey);

    protected abstract boolean isExist(Object resumeKey);

    protected abstract Object getResumeKey(String uuid);
}
