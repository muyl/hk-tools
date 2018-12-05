package com.hk.core.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;

@Slf4j
public class HttpUtil {


    public static void doAsynGet(String urlStr) {
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(urlStr)
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                log.error("onFailure: " + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                log.debug("onResponse: " + response.body().string());
            }
        });
    }

    public static void doGet(String urlStr) {
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(urlStr)
                .build();
        final Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.code());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void doPost(String urlStr, String content) {
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, content);

        Request request = new Request.Builder()
                .url(urlStr)
                .post(body)
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void doAsynPost(String urlStr,String content){
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, content);

        Request request = new Request.Builder()
                .url(urlStr)
                .post(requestBody)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                log.error("onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                log.debug(response.protocol() + " " +response.code() + " " + response.message());
                log.debug("onResponse: " + response.body().string());
            }
        });
    }


    public static void main(String[] args) {
        doGet("https://www.baidu.com");
        doAsynGet("https://www.baidu.com");
    }


}
