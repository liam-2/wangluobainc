package com.baizhi.util;


//用于多线程ping runnable类
public class PingRunnable implements Runnable{
    private PingUtil pingUtil;

    public String inputIp;
    // public String result="";

    public PingRunnable(PingUtil pingUtil, String inputIp){

        this.inputIp=inputIp;
        this.pingUtil = pingUtil;
    }


    @Override
    public void run(){

        //超时三秒
       // int timeOut = 3000;

        try {


            String result= pingUtil.getPing(inputIp);
            String [] arr=result.split("\\s+");
//            for(String ss : arr){
//                System.out.println(ss);
//            }


            if(arr[9].indexOf("100")==-1){
                System.out.println(arr[9]);
                synchronized (PingUtil.someObject) {
                    pingUtil.theAllResultList.add(inputIp);//如果丢包率100%则不加入可达ip列表
                }
            }

        }catch (Exception e) {
            System.out.println(" is interrupted when calculating, will stop...");
            return; //注意这里如果不return的话，线程还会继续执行，所以任务超时后在这里处理结果然后返回
        }



    }


}
