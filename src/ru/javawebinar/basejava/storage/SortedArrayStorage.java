package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void update(Resume r) {
        if (size == 0) return;
        int i = getIndex(r.getUuid());
        if (i < 0) return;
        storage[i] = r;
    }

    @Override
    public void save(Resume r) {
        int i = (size == 0) ? -1 : getIndex(r.getUuid());
        if (i < 0) {
            int insPoint = -(++i);
            if (size > 0) {
                if (insPoint != size) {
                    Resume[] srsArr = Arrays.copyOfRange(storage, insPoint, size);
                    System.arraycopy(srsArr, 0, storage, (insPoint + 1), srsArr.length);
                }
            }
            storage[insPoint] = r;
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
            Resume[] srsArr = Arrays.copyOfRange(storage, i + 1, size);
            System.arraycopy(srsArr, 0, storage, i, srsArr.length);
            storage[--size] = null;
        } else {
            System.out.println("Резюме " + uuid + " отсутствует!");
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
