package ru.javawebinar.basejava.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ResumeTest {
    private Resume resume = new Resume("Иванов Иван Иванович");
    private static Map<ContactType, String> mockContact = new EnumMap<>(ContactType.class);
    private static Map<SectionType, TxtBlock> mockTextBlock = new EnumMap<>(SectionType.class);

    static {
        mockContact.put(ContactType.EMAIL, "qwerty@mail.ru");
        mockContact.put(ContactType.GITHUB, "https://github.com/gkislin");
        mockContact.put(ContactType.HOMEPAGE, "http://gkislin.ru/");
        mockContact.put(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        mockContact.put(ContactType.SKYPE, "skype:grigory.kislin");
        mockContact.put(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        mockContact.put(ContactType.TELEPHONE, "+7(921) 855-0482");

        mockTextBlock.put(SectionType.PERSONAL, new LineTxtBlock("Аналитический склад ума, сильная логика, " +
                "креативность, инициативность. Пурист кода и архитектуры."));
        List<LineTxtBlock> achiv = new ArrayList<>();
        achiv.add(new LineTxtBlock("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java " +
                "Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS" +
                "/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проек" +
                "тов. Более 1000 выпускников."));
        achiv.add(new LineTxtBlock("Реализация двухфакторной аутентификации для онлайн платформы управления проект" +
                "ами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk."));
        achiv.add(new LineTxtBlock("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM" +
                ". Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: " +
                "Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интегр" +
                "ация CIFS/SMB java сервера."));
        achiv.add(new LineTxtBlock("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA," +
                " Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга."));
        achiv.add(new LineTxtBlock("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных" +
                " сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации" +
                " о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и " +
                "мониторинга системы по JMX (Jython/ Django)."));
        achiv.add(new LineTxtBlock("Реализация протоколов по приему платежей всех основных платежных системы России " +
                "(Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."));

        mockTextBlock.put(SectionType.ACHIEVEMENT, new LineListTxtBlock(achiv));

        List<LineTxtBlock> Qual = new ArrayList<>();
        Qual.add(new LineTxtBlock("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2"));
        Qual.add(new LineTxtBlock("Version control: Subversion, Git, Mercury, ClearCase, Perforce"));
        Qual.add(new LineTxtBlock("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,"));
        Qual.add(new LineTxtBlock("MySQL, SQLite, MS SQL, HSQLDB"));
        Qual.add(new LineTxtBlock("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,"));
        Qual.add(new LineTxtBlock("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,"));
        Qual.add(new LineTxtBlock("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis," +
                " Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT," +
                " ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements)."));

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

    @Before
    public void setUp() throws Exception {
        for (Map.Entry<ContactType, String> entry : mockContact.entrySet()) {
            resume.addContactBlock(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<SectionType, TxtBlock> entry : mockTextBlock.entrySet()) {
            resume.addTextBlock(entry.getKey(), entry.getValue());
        }

    }

    @Test
    public void getContact() {
        for (Map.Entry<ContactType, String> entry : mockContact.entrySet()) {
            Assert.assertEquals(entry.getValue(), resume.getContactBlock(entry.getKey()));
        }
    }

    @Test
    public void getTextBlock() {
        for (Map.Entry<SectionType, TxtBlock> entry : mockTextBlock.entrySet()) {
            Assert.assertEquals(entry.getValue(), resume.getTextBlock(entry.getKey()));
        }
    }


}