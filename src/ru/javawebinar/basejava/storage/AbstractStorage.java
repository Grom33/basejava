package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.*;
import java.util.logging.Logger;

public abstract class AbstractStorage<RK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract void updateResume(Resume r, RK resumeKey);

    protected abstract void deleteResume(RK resumeKey);

    protected abstract void insertResume(Resume r, RK resumeKey);

    protected abstract Resume getResume(RK resumeKey);

    protected abstract boolean isExist(RK resumeKey);

    protected abstract RK getResumeKey(String uuid);

    protected abstract List<Resume> getCollResume();

    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator
            .comparing(Resume::getFullName)
            .thenComparing(Resume::getUuid);

    @Override
    public void update(Resume r) {
        LOG.info("Update: "+r);
        RK resumeKey = checkExistResumeKey(r.getUuid());
        updateResume(r, resumeKey);
    }

    @Override
    public void delete(String uuid) {
        LOG.info("Delete: "+uuid);
        RK resumeKey = checkExistResumeKey(uuid);
        deleteResume(resumeKey);
    }

    @Override
    public void save(Resume r) {
        LOG.info("Save: "+r);
        RK resumeKey = checkNotExistResumeKey(r.getUuid());
        insertResume(r, resumeKey);
    }

    @Override
    public Resume get(String uuid) {
        LOG.info("Get: "+uuid);
        RK resumeKey = checkExistResumeKey(uuid);
        return getResume(resumeKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("Get all sorted");
        List<Resume> result = getCollResume();
        result.sort(RESUME_COMPARATOR);
        return result;
    }

    private RK checkExistResumeKey(String uuid) {
        RK resumeKey = getResumeKey(uuid);
        if (!isExist(resumeKey)) {
            LOG.warning("Resume: "+uuid+" not exist");
            throw new NotExistStorageException(uuid);
        }
        return resumeKey;
    }

    private RK checkNotExistResumeKey(String uuid) {
        RK resumeKey = getResumeKey(uuid);
        if (isExist(resumeKey)) {
            LOG.warning("Resume: "+uuid+" already exist");
            throw new ExistStorageException(uuid);
        }
        return resumeKey;
    }
}
