package com.lbi.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class testHttpClientUtil {
    PoolingHttpClientConnectionManager clientConnectionManager=null;
    CloseableHttpClient httpClient=null;
    String resourceName="restest";
    @Before
    public void init(){
        clientConnectionManager = new PoolingHttpClientConnectionManager();
        clientConnectionManager.setMaxTotal(300);
        clientConnectionManager.setDefaultMaxPerRoute(300);
        RequestConfig requestConfig = RequestConfig
                .copy(RequestConfig.DEFAULT)
                .setConnectionRequestTimeout(10000)
                .build();
        httpClient = HttpClients.custom()
                .setConnectionManager(clientConnectionManager)
                .setDefaultRequestConfig(requestConfig)
                .build();
    }
    @Test
    public void get(){
        String url="http://192.168.15.43:9999/api/resources?kind=1";
        HttpGet req=new HttpGet(url);
        try{
            CloseableHttpResponse resp = httpClient.execute(req);
            System.out.println(resp.getStatusLine().getStatusCode());
            System.out.println(resp.getStatusLine().toString());

            if(resp.getStatusLine().getStatusCode()==200){
                HttpEntity entity = resp.getEntity();
                if(null!=entity){
                    String content=EntityUtils.toString(entity);
                    System.out.println(content);
                }
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }

        String result=HttpClientUtil.get(url,null);
        System.out.println(result);
    }
    @Test
    public void post(){
        String url="http://192.168.15.43:9999/api/resources";
        Map<String,String> params=new HashMap<String,String>();
        params.put("name",resourceName);
        params.put("comment","test");
        params.put("kind","1");
        params.put("content","select count(1) from p_job;");


        try{
            StringEntity input=new StringEntity(JSON.toJSONString(params));
            input.setContentType("application/json");
            HttpPost req=new HttpPost(url);
            req.setEntity(input);

            CloseableHttpResponse resp = httpClient.execute(req);
            System.out.println("HttpStatus:"+resp.getStatusLine().getStatusCode());
            if(resp.getStatusLine().getStatusCode()==200){
                HttpEntity entity = resp.getEntity();
                if(null!=entity){
                    String content=EntityUtils.toString(entity);
                    System.out.println(content);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void put(){

        String url="http://192.168.15.43:9999/api/resources/"+resourceName;
        Map<String,String> params=new HashMap<String,String>();
        params.put("name",resourceName);
        params.put("comment","test123");
        params.put("kind","1");
        params.put("content","select count(1) from p_job limit 10;");


        try{

            //input.setContentType("application/json");
            HttpPut req=new HttpPut(url);
            //设置header
            req.setHeader("Content-type", "application/json");
            StringEntity stringEntity=new StringEntity(JSON.toJSONString(params),"UTF-8");
            req.setEntity(stringEntity);

            CloseableHttpResponse resp = httpClient.execute(req);
            System.out.println("HttpStatus:"+resp.getStatusLine().getStatusCode());
            if(resp.getStatusLine().getStatusCode()==200){
                HttpEntity entity = resp.getEntity();
                if(null!=entity){
                    String content=EntityUtils.toString(entity);
                    System.out.println(content);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void delete(){
        String url="http://192.168.15.43:9999/api/resources/"+resourceName;
        try{
            HttpDelete req=new HttpDelete(url);
            req.setHeader("Content-type", "application/json");
            CloseableHttpResponse resp = httpClient.execute(req);
            System.out.println("HttpStatus:"+resp.getStatusLine().getStatusCode());
            if(resp.getStatusLine().getStatusCode()==200){
                HttpEntity entity = resp.getEntity();
                if(null!=entity){
                    String content=EntityUtils.toString(entity);
                    System.out.println(content);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
