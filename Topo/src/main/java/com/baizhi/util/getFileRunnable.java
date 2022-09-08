package com.baizhi.util;

public class getFileRunnable implements Runnable{

    private HttpClientTest httpClientTest;

    public String url;

    public getFileRunnable(HttpClientTest httpClientTest,String url){
        this.httpClientTest=httpClientTest;
        this.url=url;
    }
    @Override
    public void run() {
        httpClientTest.getDownloadFile(url);
    }
}
