package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serializers.JsonStreamSerializer;
import ru.javawebinar.basejava.storage.serializers.XmlStreamSerializer;

public class JsonPathStorageTest extends AbstractStorageTest {

    public JsonPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new JsonStreamSerializer()));
    }
}
