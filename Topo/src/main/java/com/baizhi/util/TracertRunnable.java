package com.baizhi.util;

import com.baizhi.entity.TracertResult;

import java.io.IOException;
import java.util.List;

//用于多线程tracert runnable类
public class TracertRunnable implements Runnable{


    public String inputIp;

    public String startIp;

    private TracertUtil myTracert;

    public TracertRunnable(String inputIp, TracertUtil myTracert,String startIp) {
        this.inputIp = inputIp;
        this.myTracert = myTracert;
        this.startIp=startIp;
    }

    @Override
    public void run() {
        try {
            List<String> theList= myTracert.getTracert(inputIp);
            //int length=0;


            synchronized (TracertUtil.someObject2){

                int count=0;
                int length=myTracert.theTracertResultList.size();
                if(theList!=null){
                    for(int j=3;j< theList.size()-2;j++){
                        count++;
                        if(j==3){
                            myTracert.theTracertResultList.add(new TracertResult(length+count,startIp,theList.get(j)));
                        }else{
                            myTracert.theTracertResultList.add(new TracertResult(length+count,theList.get(j-1),theList.get(j)));
                        }

                    }

                }

            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
