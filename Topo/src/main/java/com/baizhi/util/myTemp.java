package com.baizhi.util;

import lombok.SneakyThrows;
import okhttp3.*;

import java.io.IOException;

public class myTemp {

    @SneakyThrows
    public static void main(String[] args) {
//        String id="20191119011310000049#7b4955e96421402191296fa3e75753fe";
//        System.out.println(id.substring(0,id.contains("#")?id.indexOf("#"):id.length()));




            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                    .url("http://192.168.2.198:8002/account/oauth/token?grant_type=authorization_code&code=vwu6XL&redirect_uri=http://www.baidu.com&client_id=insightClientId&client_secret=a8rOGGEVQ6ffTMPKlhag")
                    .method("POST", body)
                    .addHeader("Cookie", "SESSION=N2IxMThkN2QtZTJiYi00YTA5LTk4NzEtMDM2OGY2MjQzMzdj")
                    .build();
            Response response = client.newCall(request).execute();
            System.out.println(response.body().toString());



    }
}
