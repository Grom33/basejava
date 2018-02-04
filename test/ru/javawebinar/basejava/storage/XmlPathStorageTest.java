package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serializers.XmlStreamSerializer;

public class XmlPathStorageTest  extends AbstractStorageTest {

    public XmlPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new XmlStreamSerializer()));
    }
}
