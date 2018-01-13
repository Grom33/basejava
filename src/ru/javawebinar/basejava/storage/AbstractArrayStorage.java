package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size - 1, null);
        size = 0;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public void save(Resume r) {
        int i = (size == 0) ? -1 : getIndex(r.getUuid());
        if (i < 0) {
            insertResume(r, i);
            size++;
        } else {
            System.out.println("Резюме " + r + " уже есть!");
        }
    }

    @Override
    public void delete(String uuid) {
        if (size == 0) return;
        int i = getIndex(uuid);
        if (i >= 0) {
            eraseResume(i);
            --size;
        } else {
            System.out.println("Резюме " + uuid + " отсутствует!");
        }
    }

    @Override
    public void update(Resume r) {
        if (size == 0) return;
        int i = getIndex(r.getUuid());
        if (i < 0) return;
        storage[i] = r;
    }

    protected abstract void insertResume(Resume r, int insPoint);

    protected abstract void eraseResume(int i);

    protected abstract int getIndex(String uuid);
}
