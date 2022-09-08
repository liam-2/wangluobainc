package com.baizhi.service;


import com.baizhi.entity.TracertResult;
import com.baizhi.util.PingRunnable;
import com.baizhi.util.PingUtil;
import com.baizhi.util.TracertRunnable;
import com.baizhi.util.TracertUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//service实现
@Service
public class TopoServiceImpl implements TopoService {

    //获取可达ip列表
    @Override
    public List<String> getIpList(List<String> givenIpList) {

        ExecutorService exe = Executors.newFixedThreadPool(givenIpList.size());
        PingUtil pingUtil = new PingUtil();

        if (givenIpList.size() == 0) {
            return pingUtil.theAllResultList;
        }

        if (givenIpList.size() == 1) {
            return givenIpList;
        }

        for (int i = 1; i < givenIpList.size(); i++) {


            PingRunnable runnable = new PingRunnable(pingUtil, givenIpList.get(i));
            exe.execute(runnable);

        }
        exe.shutdown();
        try {
            //设置执行超时时间，ping超时自动结束线程
            if (exe.awaitTermination(5+ (int)Math.ceil((double) givenIpList.size()/10), TimeUnit.SECONDS)) {

                exe.shutdownNow();
            } else {
                exe.shutdownNow();

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            return pingUtil.theAllResultList;
        }

    }

    //获取tracert结果列表
    @Override
    public List<TracertResult> getTracertList(String startIp,List<String> givenIpList) {

        ExecutorService exe = Executors.newFixedThreadPool(givenIpList.size());
        TracertUtil myTracert=new TracertUtil();

        for(int i=0;i<givenIpList.size();i++){
            TracertRunnable tracertRunnable=new TracertRunnable(givenIpList.get(i), myTracert,startIp);
            exe.execute(tracertRunnable);
        }
        try {
            exe.shutdown();
            //设置执行超时时间，tracert超时自动结束线程
            if (exe.awaitTermination(12+givenIpList.size()*2, TimeUnit.SECONDS)) {
                exe.shutdownNow();
            } else {
                exe.shutdownNow();

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            return myTracert.theTracertResultList;
        }

    }

}
