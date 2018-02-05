package ru.javawebinar.basejava;

public class MainDeadLock {
    private static final Object LOCK = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
            try {
                getA();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
            try {
                getB();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();
        thread2.start();
    }

    private static void getA() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " try to get B");
        synchronized (LOCK) {
            Thread.sleep(200);
            getB();
            System.out.println(Thread.currentThread().getName() + " success");
        }
    }

    private static synchronized void getB() throws InterruptedException {
        //Thread.sleep(100);
        System.out.println(Thread.currentThread().getName() + " try to get A");
        getA();
    }

}
