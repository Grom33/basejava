package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serializers.ObjectStream;

public class PathStorageTest extends AbstractStorageTest {

    public PathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStream()));
    }
}
