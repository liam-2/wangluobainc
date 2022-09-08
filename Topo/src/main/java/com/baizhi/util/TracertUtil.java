package com.baizhi.util;

import com.baizhi.entity.TracertResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//模拟tracert实现类
public class TracertUtil {

    public static final Object someObject2 = new Object();//同步标志

    public List<TracertResult> theTracertResultList=new ArrayList<TracertResult>();

    String tracert = "tracert -h 10 "; //模拟tracert命令

    public StringBuffer commandResult = null;

    private List<String> readResultTracert(InputStream inputStream) throws IOException {

        commandResult = new StringBuffer();  //初始化命令行

        String commandInfo = null; //定义用于接收命令行执行结果的字符串

       // String getResult=null;

        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(inputStream,"GBK"));



       // int count=0;
        List<String> resultList=new ArrayList<String>();
        while ( (commandInfo = bufferedReader.readLine()) != null)  {

          resultList.add(commandInfo);
//            count++;
//            System.out.println(count);
           System.out.println(commandInfo);
//            if(count==9){
//                getResult=commandInfo;
//            }
            //System.out.println(commandResult);

        }
        if(resultList.get(resultList.size()-1).indexOf("完成")==-1){

            resultList=null;
           // System.out.println("66666");
        }else{
            for(int i=3;i<resultList.size()-2;i++){
                String[] aResult=resultList.get(i).split("\\s+");
                resultList.set(i,aResult[aResult.length-1]);
            }
        }
        bufferedReader.close();
        return  resultList;

    }

    public List<String> getTracert(String inputIp) throws IOException {

        String thisPing=tracert+" "+inputIp;





        List<String> myResult=new ArrayList<String>();
        Process process = Runtime.getRuntime().exec(thisPing);

        myResult=readResultTracert(process.getInputStream());

        process.destroy();

        return myResult;
    }

    public static void main(String[] args) throws UnknownHostException {



    }



}
