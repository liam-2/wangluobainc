package com.baizhi.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Test {

    public static void main(String[] args) {
//        long start = System.currentTimeMillis();
//
//        Thread thread = new TestThread();
//        thread.start();
//
//        long end = System.currentTimeMillis();
//        System.out.println("子线程执行时长：" + (end - start));
//        long start = System.currentTimeMillis();
//
//        List<Thread> list = new ArrayList<Thread>();
//        for(int i = 0; i < 5; i++)
//        {
//            Thread thread = new TestThread();
//            thread.start();
//            list.add(thread);
//        }
//
//        try
//        {
//            for(Thread thread : list)
//            {
//                thread.join();
//            }
//        }
//        catch (InterruptedException e)
//        {
//            e.printStackTrace();
//        }
//
//        long end = System.currentTimeMillis();
//        System.out.println("子线程执行时长：" + (end - start));
        long start = System.currentTimeMillis();

        // 创建一个初始值为5的倒数计数器
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for(int i = 0; i < 5; i++)
        {
            Thread thread = new TestThread(countDownLatch);
            thread.start();
        }

        try
        {
            // 阻塞当前线程，直到倒数计数器倒数到0
            countDownLatch.await();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("子线程执行时长：" + (end - start));
    }
}
