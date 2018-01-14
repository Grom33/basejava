package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Test;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class ArrayStorageTest extends AbstractArrayStorageTest {

    public ArrayStorageTest() {
        super(new ArrayStorage());
    }

    @Test
    public void getAll() {
       Resume[] mock =  super.getMockArr();
        Assert.assertArrayEquals(mock, super.storageGetAll());
    }
}