package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Test;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorageTest extends AbstractArrayStorageTest {

    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }

    @Test
    public void getAll() {
        Resume[] mock = super.getMockArr();
        Arrays.sort(mock);
        Assert.assertArrayEquals(mock, super.storageGetAll());
    }
}