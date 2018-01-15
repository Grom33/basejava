package ru.javawebinar.basejava.storage;

import org.junit.*;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    private static final Resume UUID_1 = new Resume("UUID1");
    private static final Resume UUID_2 = new Resume("UUID2");
    private static final Resume UUID_3 = new Resume("UUID3");

    public AbstractArrayStorageTest(Storage str) {
        this.storage = str;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(UUID_1);
        storage.save(UUID_2);
        storage.save(UUID_3);
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
        Assert.assertEquals(UUID_2, storage.get("UUID2"));
    }

    @Test
    public void save() {
        Resume r = new Resume("test");
        storage.save(r);
        Assert.assertEquals(r, storage.get(r.getUuid()));
    }

    @Test (expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_2.getUuid());
        storage.get(UUID_2.getUuid());
    }

    @Test
    public void update() {
        //storage.update(r);
        //Assert.assertEquals(r, storage.get(r.getUuid()));
    }

    @Test
    public void getAll() {
        Assert.assertEquals(3, storage.size());
        storage.get(UUID_1.getUuid());
        storage.get(UUID_2.getUuid());
        storage.get(UUID_3.getUuid());
    }

    @Test(expected = ExistStorageException.class)
    public void resumeAllrExist() {
        storage.save(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test(expected = StorageException.class)
    public void storageOverFlow() {
        for (int i=4; i<=10001;i++){
            storage.save(new Resume());
        }
    }
}