package com.example.myutil.thread;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;


import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

@ThreadSafe // 没有实际功效，只用来标注
public class SynchronizedFactorizer  {
    // @GuardedBy没有实际功效，只用来标注
    @GuardedBy("b1") public   BigInteger lastNumber;
    @GuardedBy("b2") public BigInteger[] lastFactors;

    public final static SynchronizedFactorizer b1 = new SynchronizedFactorizer();
    public final static SynchronizedFactorizer b2 = new SynchronizedFactorizer();


    public static void main(String[] args) {
        ThreaRun runable = new ThreaRun();
        Thread thread1 = new Thread(runable);

        thread1.start();

        synchronized (b1) {

            b2.lastNumber = new BigInteger("2");
            try {
                System.out.println("等三秒22222222222222");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(b2.lastNumber);
        }
    }
}
