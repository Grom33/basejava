package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public abstract class AbstractStorage implements Storage {

    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator
            .comparing(Resume::getFullName)
            .thenComparing(Resume::getUuid);

    @Override
    public void update(Resume r) {
        Object resumeKey = checkExistResumeKey(r.getUuid());
        updateResume(r, resumeKey);
    }

    @Override
    public void delete(String uuid) {
        Object resumeKey = checkExistResumeKey(uuid);
        deleteResume(resumeKey);
    }

    @Override
    public void save(Resume r) {
        Object resumeKey = checkNotExistResumeKey(r.getUuid());
        insertResume(r, resumeKey);
    }

    @Override
    public Resume get(String uuid) {
        Object resumeKey = checkExistResumeKey(uuid);
        return getResume(resumeKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> result = getCollResume();
        result.sort(RESUME_COMPARATOR);
        return result;
    }

    private Object checkExistResumeKey(String uuid) {
        Object resumeKey = getResumeKey(uuid);
        if (!isExist(resumeKey)) throw new NotExistStorageException(uuid);
        return resumeKey;
    }


    private Object checkNotExistResumeKey(String uuid) {
        Object resumeKey = getResumeKey(uuid);
        if (isExist(resumeKey)) throw new ExistStorageException(uuid);
        return resumeKey;
    }

    protected abstract void updateResume(Resume r, Object resumeKey);

    protected abstract void deleteResume(Object resumeKey);

    protected abstract void insertResume(Resume r, Object resumeKey);

    protected abstract Resume getResume(Object resumeKey);

    protected abstract boolean isExist(Object resumeKey);

    protected abstract Object getResumeKey(String uuid);

    protected abstract ArrayList<Resume> getCollResume();
}
