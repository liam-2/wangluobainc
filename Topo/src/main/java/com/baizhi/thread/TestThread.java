package com.baizhi.thread;

import java.util.concurrent.CountDownLatch;

public class TestThread extends Thread {


    private CountDownLatch countDownLatch;

    public TestThread(CountDownLatch countDownLatch)
    {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run()
    {
        System.out.println(this.getName() + "子线程开始");
        try
        {
            // 子线程休眠五秒
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(this.getName() + "子线程结束");
        // 倒数器减1
        countDownLatch.countDown();
    }
}
