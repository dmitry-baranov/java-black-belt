package org.example.multithreading.deadlock;

public class ExDeadLock {

    public static final Object lock1 = new Object();
    public static final Object lock2 = new Object();


    public static void main(String[] args) {
        Thread10 thread10 = new Thread10();
        Thread20 thread20 = new Thread20();
        thread10.start();
        thread20.start();
    }

    /*
    The solution:
    We need to follow the blocking sequence
     */
    static class Thread10 extends Thread{
        public void run() {
            System.out.println("Thread10: Try to take monitor of object lock1");
            synchronized (ExDeadLock.lock1){
                System.out.println("Thread10: monitor of object lock1 was taken");
                System.out.println("Thread10: Try to take monitor of object lock2");
                synchronized (ExDeadLock.lock2) {
                    System.out.println("Thread10: monitor of object lock1 and lock2" +
                            " was taken");
                }
            }
        }
    }
    static class Thread20 extends Thread{
        public void run() {
            System.out.println("Thread20: Try to take monitor of object lock2");
            synchronized (ExDeadLock.lock2){
                System.out.println("Thread20: monitor of object lock2 was taken");
                System.out.println("Thread20: Try to take monitor of object lock1");
                synchronized (ExDeadLock.lock1) {
                    System.out.println("Thread20: monitor of object lock1 and lock2" +
                            " was taken");
                }
            }
        }
    }
}
