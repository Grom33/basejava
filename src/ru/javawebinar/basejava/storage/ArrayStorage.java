package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    protected void insertResume(Resume r, int insPoint) {
        storage[size] = r;
    }

    protected void eraseResume(int i) {
        storage[i] = storage[size - 1];
        storage[size - 1] = null;
    }

    protected int getIndex(String f) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(f)) {
                return i;
            }
        }
        return -1;
    }

}
