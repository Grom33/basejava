package ru.javawebinar.basejava.model;

public enum ContactType {
    TELEPHONE("Тел."),
    SKYPE("Skype"),
    EMAIL("Почта"),
    LINKEDIN("Профиль на LinkedIn"),
    GITHUB("Профиль на GitHub"),
    STACKOVERFLOW("Профиль на Stackoverflow"),
    HOMEPAGE("Дмашняя страница");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
