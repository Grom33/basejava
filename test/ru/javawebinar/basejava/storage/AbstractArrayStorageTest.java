package ru.javawebinar.basejava.storage;

import org.junit.*;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    private static final String UUID_1 = "UUID1";
    private static final String UUID_2 = "UUID7";
    private static final String UUID_3 = "UUID3";
    private Resume[] MockArr = new Resume[3];

    public AbstractArrayStorageTest(Storage str) {
        this.storage = str;
    }

    public Resume[] getMockArr() {
        return MockArr;
    }

    public Resume[] storageGetAll() {
        return storage.getAll();
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
        MockArr[0] = new Resume(UUID_1);
        MockArr[1] = new Resume(UUID_2);
        MockArr[2] = new Resume(UUID_3);
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Assert.assertEquals(UUID_2, storage.get("UUID7").toString());
    }

    @Test
    public void save() {
        Resume r = new Resume("test");
        storage.save(r);
        Assert.assertTrue((Arrays.binarySearch(storage.getAll(), r) > 0));
    }

    @Test
    public void delete() {
        Resume r = new Resume(UUID_2);
        storage.delete(UUID_2);
        Assert.assertTrue((Arrays.binarySearch(storage.getAll(), r) < 0));
    }

    @Test
    public void update() {
        Resume r = new Resume(UUID_2);
        storage.update(r);
        Assert.assertEquals(r, storage.get(r.getUuid()));
    }

    @Test(expected = ExistStorageException.class)
    public void resumeAlrExst() {
        Resume r = new Resume(UUID_2);
        storage.save(r);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }


}