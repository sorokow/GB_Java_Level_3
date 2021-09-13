package com.company.LessonFour;

public class AppABC {
    private static Object object = new Object();
    private static Object object2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            try {
                writeA();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                writeB();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread3 = new Thread(() -> {
            try {
                writeC();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }

    public static void writeA() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            synchronized (object) {
                synchronized (object2) {
                    object.notify();
                    object2.notify();
                    System.out.print("A");
                    object.wait();
                    object2.wait();
                }
            }
        }
    }

    public static void writeB() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            synchronized (object) {
                    object.notify();
                    System.out.print("B");
                    object.wait();

            }
        }
    }

    public static void writeC() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            synchronized (object2) {
                    object2.notify();
                    System.out.print("C");
                    object2.wait();
            }
        }
    }
}
