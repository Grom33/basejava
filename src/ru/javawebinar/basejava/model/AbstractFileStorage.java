package ru.javawebinar.basejava.model;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.storage.AbstractStorage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private File resumeDir;

    protected abstract void writeFile(Resume r, File resumeKey);

    protected abstract Resume readFile(File resumeKey);

    public AbstractFileStorage(File resumeFile) {
        Objects.requireNonNull(resumeFile, "directory must not be null");
        if (!resumeFile.isDirectory()) {
            throw new IllegalArgumentException("it is not a directory");
        }
        if (!resumeFile.canRead() || !resumeFile.canWrite()) {
            throw new IllegalArgumentException("Access denied");
        }
        this.resumeDir = resumeFile;
    }

    @Override
    protected void updateResume(Resume r, File resumeKey) {
        try {
            writeFile(r, resumeKey);
        }catch (Exception e){
            throw new StorageException("Can't write file", r.getUuid(),e);
        }

    }

    @Override
    protected void deleteResume(File resumeKey) {
        if (!resumeKey.delete()) {
            throw new StorageException("File delete error", resumeKey.getName());
        }
    }

    @Override
    protected Resume getResume(File resumeKey) {
        return readFile(resumeKey);
    }

    @Override
    protected boolean isExist(File resumeKey) {
        return resumeDir.exists();
    }

    @Override
    protected File getResumeKey(String uuid) {
        return new File(resumeDir, uuid);
    }

    @Override
    protected List<Resume> getCollResume() {
        File[] files = resumeDir.listFiles();
        if (files == null) {
            throw new StorageException("Directory is null", null);
        }
        List<Resume> list = new ArrayList<>(files.length);
        for (File file : files) {
            list.add(getResume(file));
        }
        return list;
    }

    @Override
    public void clear() {
        File[] files = resumeDir.listFiles();
        if (files != null) {
            for (File file : files) {
                deleteResume(file);
            }
        }
    }

    @Override
    public int size() {
        return Objects.requireNonNull(resumeDir.list()).length;
    }
}
