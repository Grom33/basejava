package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.storage.serializers.XmlStreamSerializer;

public class SqlStorageTest extends AbstractStorageTest {

    public SqlStorageTest() throws ClassNotFoundException {
        super(Config.get().getStorage());
    }


}
