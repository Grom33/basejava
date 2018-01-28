package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {

    public static void main(String[] args) {
/*        String filePath = ".\\.gitignore";

        File file = new File(filePath);
*//*        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }*//*

        File dir = new File("./src/ru/javawebinar/basejava");
        //      System.out.println(dir.isDirectory());
        String[] list = dir.l.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        printDir(".\\\\.");
    }

    private static void printDir(String path) {
        File dir = new File(path);
        File[] list = dir.listFiles();

        if (list != null) {
            for (File name : list) {
                if (name.isDirectory()) {
                    try {
                        System.out.println(name.getCanonicalPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    printDir(name.getPath());
                } else {
                    try {
                        System.out.println(name.getCanonicalPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

}

