package com.hk.core.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shanks on 2020-01-16
 */

public class HttpClientUtil {

    private static       Logger              log        = LoggerFactory.getLogger(HttpClientUtil.class);
    private final static Lock                lock       = new ReentrantLock();
    private static       CloseableHttpClient httpClient = null;

    static {
        init();
    }

    public static void init(){
        lock.lock();
        try{
            if(httpClient == null){
                httpClient = HttpClients.createDefault();
            }
        }catch (Exception e){
            LoggerFactory.getLogger(HttpClientUtil.class).error("HttpClient 初始化异常：" + e.toString());
        }finally {
            lock.unlock();
        }
    }

    /**
     * 发送get请求
     * @param url
     * @param decodeCharset
     * @return
     */
    public static String sendGetRequest(String url, String decodeCharset) {
        String responseContent = null;
        HttpGet httpGet = new HttpGet(url);
        HttpEntity entity = null;
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            entity = response.getEntity();
            if (null != entity) {
                responseContent = EntityUtils.toString(entity, decodeCharset == null ? "UTF-8" : decodeCharset);
            }
        } catch (Exception e) {
            log.error("HttpClient GET 请求异常：", e);
        } finally {
            try {
                EntityUtils.consume(entity);
                response.close();
            } catch (Exception e) {
                log.error("HttpClient GET response关闭异常：", e);
            }
        }
        return responseContent;
    }

    /**
     * 发送get请求
     * @param url
     * @return
     */
    public static String sendGetRequest(String url) {
        return sendGetRequest(url, "UTF-8");
    }

    public static String sendHttpPostRequest(String reqURL, String data) {
        CloseableHttpResponse response = null;
        String respStr = "";
        try {
            HttpPost httppost = new HttpPost(reqURL);
            StringEntity strEntity = new StringEntity(data, "UTF-8");
            strEntity.setContentType("application/x-www-form-urlencoded");
            httppost.setEntity(strEntity);
            log.info(EntityUtils.toString(strEntity));
            log.info("executing request " + httppost.getRequestLine());

            response = httpClient.execute(httppost);
            HttpEntity resEntity = response.getEntity();

            if (resEntity != null) {
                respStr = EntityUtils.toString(resEntity);
            }

        } catch (Exception e) {
            log.error("HttpClient POST 请求异常：", e);
        }finally {
            try {
                response.close();
            } catch (IOException e) {
                log.error("HttpClient POST response关闭异常：", e);
            }
        }
        return respStr;
    }

    /**
     * 发送post请求
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public static String sendHttpPostRequest(String url, Map<String, String> params) {
        String respStr = "";
        CloseableHttpResponse response = null;
        try {
            HttpPost post = new HttpPost(url);
            List<BasicNameValuePair> postData = new ArrayList<BasicNameValuePair>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                postData.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(postData, "UTF-8");
            post.setEntity(entity);
            response = httpClient.execute(post);
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                respStr = EntityUtils.toString(resEntity);
            }
        } catch (Exception e) {
            log.error("HttpClient POST 请求异常：", e);
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                log.error("HttpClient POST response关闭异常：", e);
            }
        }
        return respStr;
    }
}
