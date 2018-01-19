package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Test;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest{


    public AbstractArrayStorageTest(Storage str) {
        super(str);
    }

    @Test(expected = StorageException.class)
    public void storageOverFlow() {
        try {
            for (int i = 4; i <= 10000; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume());
    }
}