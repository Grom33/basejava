package ru.javawebinar.basejava.storage;

import org.junit.*;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractStorageTest {
    protected Storage storage;

    private static final Resume UUID_1 = new Resume("UUID1", "Ivan Ivanov");
    private static final Resume UUID_2 = new Resume("UUID2", "Petr Petrov");
    private static final Resume UUID_3 = new Resume("UUID3", "Sergei Sergeev");
    private static final Resume TEST = new Resume("test", "Test Testovich");

    public AbstractStorageTest(Storage str) {
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
        storage.save(TEST);
        Assert.assertEquals(TEST, storage.get(TEST.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
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

    @Test
    public void getAllSorted() {
        System.out.println(storage.getAllSorted());
    }


}