package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    protected void insertResume(Resume r, int i) {
        int insPoint = -(++i);
        if (insPoint != size) {
            System.arraycopy(storage, insPoint, storage, (insPoint + 1), size - i);
        }
        storage[insPoint] = r;
    }

    protected void eraseResume(int i) {
        System.arraycopy(storage, i + 1, storage, i, size - i);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
