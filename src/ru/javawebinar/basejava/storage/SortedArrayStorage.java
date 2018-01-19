package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected void insertResumeToStorage(Resume r, int i) {
        int insPoint = -i - 1;
        System.arraycopy(storage, insPoint, storage, insPoint + 1, size - insPoint);
        storage[insPoint] = r;
    }

    @Override
    protected void eraseResume(int i) {
        System.arraycopy(storage, i + 1, storage, i, size - i);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
