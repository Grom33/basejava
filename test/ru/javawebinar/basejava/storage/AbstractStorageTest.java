package ru.javawebinar.basejava.storage;

import org.junit.*;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.util.*;

public abstract class AbstractStorageTest {
    protected Storage storage;

    private static  Resume UUID_1 = new Resume("UUID1", "Ivan Ivanov");
    private static  Resume UUID_2 = new Resume("UUID2", "Petr Petrov");
    private static  Resume UUID_3 = new Resume("UUID3", "Sergei Sergeev");
    private static  Resume TEST = new Resume("test", "Test Testovich");
    //private Resume resume = new Resume("Иванов Иван Иванович");
    private static Map<ContactType, String> mockContact = new EnumMap<>(ContactType.class);
    private static Map<SectionType, TxtBlock> mockTextBlock = new EnumMap<>(SectionType.class);

    static {
        mockContact.put(ContactType.EMAIL, "qwerty@mail.ru");
        mockContact.put(ContactType.GITHUB, "https://github.com/gkislin");

        mockTextBlock.put(SectionType.PERSONAL, new LineTxtBlock("Аналитический склад ума, сильная логика, " +
                "креативность, инициативность. Пурист кода и архитектуры."));
        List<String> achiv = new ArrayList<>();
        achiv.add(("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java " +
                "Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS" +
                "/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проек" +
                "тов. Более 1000 выпускников."));
        achiv.add(("Реализация двухфакторной аутентификации для онлайн платформы управления проект" +
                "ами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk."));


        mockTextBlock.put(SectionType.ACHIEVEMENT, new LineListTxtBlock(achiv));

        List<String> Qual = new ArrayList<>();
        Qual.add(("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2"));
        Qual.add(("Version control: Subversion, Git, Mercury, ClearCase, Perforce"));
        Qual.add(("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,"));

        mockTextBlock.put(SectionType.QUALIFICATIONS, new LineListTxtBlock(Qual));

        List<Organization> orgs = new ArrayList<>();

        orgs.add(new Organization("Java Online Projects", new SkillsItem(LocalDate.of(2013, 10, 1), LocalDate.now(), "Автор проекта",
                "Создание, организация и проведение Java онлайн проектов и стажировок.")));

        mockTextBlock.put(SectionType.EXPERIENCE, new OrganizationTxtBlock(orgs));

        orgs.clear();

        orgs.add(new Organization("JСанкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики",
                new SkillsItem(LocalDate.of(1993, 9, 1), LocalDate.of(1996, 7, 1)
                        , "ААспирантура (программист С, С++)")));
        orgs.get(0).addSkill(LocalDate.of(1987, 9, 1), LocalDate.of(1993, 7, 1)
                , "\tИнженер (программист Fortran, C)");
        mockTextBlock.put(SectionType.EDUCATION, new OrganizationTxtBlock(orgs));
    }


    public AbstractStorageTest(Storage str) {
        this.storage = str;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        for (Map.Entry<ContactType, String> entry : mockContact.entrySet()) {
            UUID_1.addContactBlock(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<SectionType, TxtBlock> entry : mockTextBlock.entrySet()) {
            UUID_1.addTextBlock(entry.getKey(), entry.getValue());
        }
        storage.save(UUID_1);
        storage.save(UUID_2);
        storage.save(UUID_3);
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
        Assert.assertEquals(UUID_2, storage.get("UUID2"));
    }

    @Test
    public void save() {
        storage.save(TEST);
        Assert.assertEquals(TEST, storage.get(TEST.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_2.getUuid());
        storage.get(UUID_2.getUuid());
    }

    @Test
    public void update() {
        //storage.update(r);
        //Assert.assertEquals(r, storage.get(r.getUuid()));
    }

    @Test
    public void getAll() {
        Assert.assertEquals(3, storage.size());
        storage.get(UUID_1.getUuid());
        storage.get(UUID_2.getUuid());
        storage.get(UUID_3.getUuid());
    }

    @Test(expected = ExistStorageException.class)
    public void resumeAllrExist() {
        storage.save(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void getAllSorted() {
        ArrayList<Resume> sortedList = new ArrayList<>(storage.getAllSorted());
        Assert.assertEquals(UUID_1, sortedList.get(0));
        Assert.assertEquals(UUID_2, sortedList.get(1));
        Assert.assertEquals(UUID_3, sortedList.get(2));
    }


}