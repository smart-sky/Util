package com.example.myutil.thread;

import java.math.BigInteger;

public class ThreaRun implements Runnable{
    @Override
    public void run() {
        synchronized (SynchronizedFactorizer.b2) {
            try {
                System.out.println("等2秒11111111111111");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SynchronizedFactorizer.b2.lastNumber = new BigInteger("3");
            System.out.println(SynchronizedFactorizer.b2.lastNumber);
        }
    }
}
