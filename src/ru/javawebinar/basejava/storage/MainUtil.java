package ru.javawebinar.basejava.storage;

public class MainUtil {
    public static void main(String[] args) {
       // System.out.println(Integer.valueOf(-1) == Integer.valueOf(-1));
       // System.out.println(Integer.valueOf(-1) == new Integer(-1));
       // int result = getInt();
       // System.out.println(result);
        System.out.println(Runtime.getRuntime().freeMemory());
        System.out.println(Runtime.getRuntime().maxMemory());
        System.out.println(Runtime.getRuntime().totalMemory());

    }

    private static Integer getInt() {
        return null;
    }
}
