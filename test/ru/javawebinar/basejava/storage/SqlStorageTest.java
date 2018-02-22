package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.storage.serializers.XmlStreamSerializer;

public class SqlStorageTest extends AbstractStorageTest {

    public SqlStorageTest() throws ClassNotFoundException {
        super(new SqlStorage(
                Config.get().getProps().getProperty("db.url"),
                Config.get().getProps().getProperty("db.user"),
                Config.get().getProps().getProperty("db.password")));
    }


}
