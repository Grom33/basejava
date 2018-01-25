package ru.javawebinar.basejava.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                ListStorageTest.class,
                SortedStorageTest.class,
                StorageTest.class,
                MapResumeStorageTest.class,
                MapUUIDStorageTest.class
        })

public class StorageSuitTest {
}
