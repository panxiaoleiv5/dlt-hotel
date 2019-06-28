package com.handinglian.common.utils;

import com.alibaba.fastjson.JSON;
import com.handinglian.common.exception.KkBizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.dom4j.DocumentException;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

/**
 * @description: HttpClient请求类
 * @author: pxl
 * @create: 2019/6/3 11:18
 */
@Slf4j
public class HttpUtil {

    private static HttpClient getHttpClient(){
        HttpClient client = new DefaultHttpClient();
        SSLContext sslcontext = null;
        try {
            sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(null, null, null);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        SSLSocketFactory sf = new SSLSocketFactory(sslcontext,
                SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        Scheme sch = new Scheme("https", 443, sf);
        client.getConnectionManager().getSchemeRegistry().register(sch);
        return client;
    }

    /**
    * 通过Post方式请求控客接口
    * @author pxl
    * @param url 控客接口url
    * @param params json参数
    * @return java.lang.String
    * @date 2019/6/3 11:16
    */
    public static String requestPostJson(String url, Map<String, Object> params) throws IOException, DocumentException {
        HttpClient httpClient = getHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("appId", "10001166");
        httpPost.setHeader("appKey", "aea40c20-57f6-4df2-95fb-02cb9fc4eb5a");
        httpPost.setHeader("Host", "api.developer.hijaytech.com");

        StringEntity entity = new StringEntity(JSON.toJSONString(params), HTTP.UTF_8);
        httpPost.setEntity(entity);
        try {
            HttpResponse response = httpClient.execute(httpPost);

            if (response != null) {
                String result = EntityUtils.toString(response.getEntity(),"utf-8");
                return result == null?"":result;
            } else {
                throw KkBizException.REQUEST_FAILURE;
            }
        } finally {
            if (httpPost != null){
                httpPost.releaseConnection();
            }
        }

    }

    /**
    * 通过Get方式请求控客接口
    * @author pxl
    * @param url 控客接口url
    * @return java.lang.String
    * @date 2019/6/3 11:17
    */
    public static String requestGet(String url) throws IOException {
        HttpClient httpClient = getHttpClient();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Content-Type", "application/json");
        httpGet.setHeader("appId", "10001166");
        httpGet.setHeader("appKey", "aea40c20-57f6-4df2-95fb-02cb9fc4eb5a");
        httpGet.setHeader("Host", "api.developer.hijaytech.com");

        try {
            HttpResponse response = httpClient.execute(httpGet);

            if (response != null) {
                String result = EntityUtils.toString(response.getEntity(),"utf-8");
                return result == null?"":result;
            } else {
                throw KkBizException.REQUEST_FAILURE;
            }
        } finally {
            if (httpGet != null){
                httpGet.releaseConnection();
            }
        }

    }

    /**
    * 通过Put方式请求控客接口
    * @author pxl
    * @param url
    * @return java.lang.String
    * @date 2019/6/4 13:34
    */
    public static String requestPut(String url) throws IOException {
        HttpClient httpClient = getHttpClient();
        HttpPut httpPut = new HttpPut(url);
        httpPut.setHeader("Content-Type", "application/json");
        httpPut.setHeader("appId", "10001166");
        httpPut.setHeader("appKey", "aea40c20-57f6-4df2-95fb-02cb9fc4eb5a");
        httpPut.setHeader("Host", "api.developer.hijaytech.com");

        try {
            HttpResponse response = httpClient.execute(httpPut);

            if (response != null) {
                String result = EntityUtils.toString(response.getEntity(),"utf-8");
                return result == null?"":result;
            } else {
                throw KkBizException.REQUEST_FAILURE;
            }
        } finally {
            if (httpPut != null){
                httpPut.releaseConnection();
            }
        }

    }

    /**
    * 通过Delete方式请求控客接口
    * @author pxl
    * @param url 控客接口url
    * @return java.lang.String
    * @date 2019/6/3 11:18
    */
    public static String requestDelete(String url) throws IOException, DocumentException {
        HttpClient httpClient = getHttpClient();

        HttpDelete httpDelete  = new HttpDelete(url);
        httpDelete.setHeader("Content-Type", "application/json");
        httpDelete.setHeader("appId", "10001166");
        httpDelete.setHeader("appKey", "aea40c20-57f6-4df2-95fb-02cb9fc4eb5a");
        httpDelete.setHeader("Host", "api.developer.hijaytech.com");

        try {
            HttpResponse response = httpClient.execute(httpDelete);
            if (response != null) {
                String result = EntityUtils.toString(response.getEntity(),"utf-8");
                return result == null?"":result;
            } else {
                throw KkBizException.REQUEST_FAILURE;
            }
        } finally {
            if (httpDelete != null){
                httpDelete.releaseConnection();
            }
        }

    }

    /**
    * 对response进行处理，获取目标对象
    * @author pxl
    * @param response request请求的返回结果
    * @param key 控客接口的key
    * @param clazz 目标对象的类对象, 无目标对象传null
    * @return T
    * @date 2019/6/4 11:04
    */
    public static <T> T responseProcess(String response, String key, Class<T> clazz){
        int index = response.indexOf("error_code");
        if (index == -1) {
            if (clazz != null){
                return JSON.parseObject(response, clazz);
            } else {
                return null;
            }

        } else {
            log.error("kongke "+key+" error:" + response);
            throw KkBizException.REQUEST_FAILURE;
        }
    }

    /**
     * 对response进行处理，获取目标List
     * @author pxl
     * @param response request请求的返回结果
     * @param key 控客接口的key
     * @param clazz 目标对象的类对象, 无目标对象传null
     * @return List<T>
     * @date 2019/6/4 11:04
     */
    public static <T> List<T> responseListProcess(String response, String key, Class<T> clazz){
        int index = response.indexOf("error_code");
        if (index == -1) {
            if (clazz != null){
                return JSON.parseArray(response, clazz);
            } else {
                return null;
            }

        } else {
            log.error("kongke "+key+" error:" + response);
            throw KkBizException.REQUEST_FAILURE;
        }
    }

}
