package com.example.myutil.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadStateTest {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(3000);
                log.info("111");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1");
        t1.start();


        try {
            log.info(String.valueOf(t1.getState()));
            log.info("主线程");
            Thread.sleep(1000);
            log.info(String.valueOf(t1.getState()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
