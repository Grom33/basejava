package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage{

    public void clear() {
        Arrays.fill(storage, 0,size-1, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            //storage[index] = newR;
        } else {
            System.out.println("Такого резюме нет в базе!");
        }

    }

    public void save(Resume r) {

        if (size <= storage.length) {
            if (getIndex(r.getUuid()) < 0) {
                storage[size++] = r;
            } else {
                System.out.println("Такое резюме уже есть!");
            }
        } else {
            System.out.println("Список резюме переполнен!");
        }
    }

    public Resume get(String uuid) {
        int res = getIndex(uuid);
        if (res < 0) System.out.println("Резюме не найдено!");
        return res >= 0 ? storage[res] : null;
    }

    public void delete(String uuid) {
        int res;
        res = getIndex(uuid);
        if (res >= 0) {     //
            storage[res] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Такого резюме не существует!");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
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
