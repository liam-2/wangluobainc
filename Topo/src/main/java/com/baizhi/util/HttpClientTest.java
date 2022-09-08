package com.baizhi.util;


import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientTest {

   // public static final Object someObject3 = new Object();//同步标志

    public  String getTheFlashUrl(String aurl){
        String fixedUrl="http://fxdj1.zxcmk.com/";
        int start=aurl. lastIndexOf("Address")+9;
        int end=aurl.indexOf("TypeID")-2;

        return fixedUrl+aurl.substring(start,end);
    }

    public void  getDownloadFile(String url){
        //1.生成httpclient，相当于该打开一个浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        HttpGet request = new HttpGet(url);
        try {
            //3.执行get请求，相当于在输入地址栏后敲回车键
            response = httpClient.execute(request);

            //4.判断响应状态为200，进行处理
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //5.获取响应内容
                HttpEntity httpEntity = response.getEntity();
                String html = EntityUtils.toString(httpEntity, "utf-8");
                String getUrl=new HttpClientTest().getTheFlashUrl(html);
                int le=getUrl.length();
                System.out.println("url是"+getUrl);
                new AutoGetZip().downLoadFromUrl(getUrl,getUrl.substring(23,le),"D:\\download\\flash\\");
                System.out.println("下载完成");
                //System.out.println("url是"+HttpClientTest.getTheFlashUrl(html));
                //.out.println(html);
            } else {
                //如果返回状态不是200，比如404（页面不存在）等，根据情况做处理，这里略
                System.out.println("返回状态不是200");
                System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //6.关闭
            HttpClientUtils.closeQuietly(response);
            HttpClientUtils.closeQuietly(httpClient);
        }
    }



    public static void main(String[] args) {


        ExecutorService exe = Executors.newFixedThreadPool(100);
        for (int i = 30000; i < 120000; i++) {

            getFileRunnable runnable = new getFileRunnable(new HttpClientTest(), "https://www.fxxz.com/yx/" + i + ".html");
            exe.execute(runnable);

//        getFileRunnable runnable=new getFileRunnable(new HttpClientTest(),"https://www.fxxz.com/yx/"+93903+".html");
            //    exe.execute(runnable);
        }
        exe.shutdown();
        try {
            //设置执行超时时间，ping超时自动结束线程
            if (exe.awaitTermination(3600, TimeUnit.SECONDS)) {

                exe.shutdownNow();
            } else {
                exe.shutdownNow();

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
