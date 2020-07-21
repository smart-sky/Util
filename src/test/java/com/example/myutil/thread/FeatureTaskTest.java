package com.example.myutil.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j
public class FeatureTaskTest {

    static FutureTask<String> future = new FutureTask(new Callable<String>(){
        public String call() throws InterruptedException {
            log.info("{}","在睡觉");
            Thread.sleep(2000);
            return getPageContent();
        }
    });

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //Start a thread to let this thread to do the time exhausting thing
        new Thread(future,"t1").start();
        //Main thread can do own required thing first
        log.info("{}",doOwnThing());
        //At the needed time, main thread can get the result
        log.info("{}",future.get());
        log.info("{}","完成");
    }

    public static String doOwnThing(){
        return "Do Own Thing";
    }
    public static String getPageContent(){
        return "testPageContent and provide that the operation is a time exhausted thing...";
    }

//    FutureTask有下面几个重要的方法：
//
//            1.get()
//
//    阻塞一直等待执行完成拿到结果
//
//
//
//2.get(int timeout, TimeUnit timeUnit)
//
//    阻塞一直等待执行完成拿到结果，如果在超时时间内，没有拿到抛出异常
//
//
//
//3.isCancelled()
//
//    是否被取消
//
//
//
//4.isDone()
//
//    是否已经完成
//
//
//
//5.cancel(boolean mayInterruptIfRunning)
//
//    试图取消正在执行的任务
}
