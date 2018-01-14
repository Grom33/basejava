package ru.javawebinar.basejava.storage;

import org.junit.*;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    private static final String UUID_1 = "UUID1";
    private static final String UUID_2 = "UUID7";
    private static final String UUID_3 = "UUID3";
    private Resume[] MockArr = new Resume[3];

    public AbstractArrayStorageTest(Class clss) {
        if (clss.equals(ArrayStorageTest.class)) {
            this.storage = new ArrayStorage();
        } else if (clss.equals(SortedArrayStorageTest.class)) {
            this.storage = new SortedArrayStorage();
        }
    }

    public Resume[] getMockArr(){
        return MockArr;
    }
    public Resume[] storageGetAll(){
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
        Assert.assertTrue((Arrays.binarySearch(storage.getAll(), r)>0));
    }

    @Test
    public void delete() {
        Resume r = new Resume(UUID_2);
        storage.delete(UUID_2);
        Assert.assertTrue((Arrays.binarySearch(storage.getAll(), r)<0));
    }

    @Test
    public void update() {

    }


}