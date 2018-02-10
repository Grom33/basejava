package ru.javawebinar.basejava;

public class MainDeadLock {
    private static final Object LOCK = new Object();

    private static final int A = 1;
    private static final int B = 1;

    public static void main(String[] args) {

        A aa = new A();
        A bb = new A();

        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
            try {
                System.out.println("Result "+aa.getX(bb));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
            try {
                System.out.println("Result "+bb.getX(aa));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
       thread1.start();
        thread2.start();
    }

    private static class A {
        private int x = 1;

        synchronized int getX(A y) throws InterruptedException {
            Thread.sleep(50);
            System.out.println(Thread.currentThread().getName() + " try to get result");
            return y.get()+this.get();
            //return x + y.get();
        }

        synchronized public int get() {
            return x;
        }
    }


}
