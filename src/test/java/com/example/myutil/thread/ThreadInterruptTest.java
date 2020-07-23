package com.example.myutil.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadInterruptTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(3000);
                log.info("111");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1");
        t1.start();
        Thread.sleep(500);
        t1.interrupt();

    }
}
