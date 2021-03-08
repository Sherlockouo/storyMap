package com.storymap.util.common;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @description: desc
 * @author: wdf
 * @email: wdf.coder@gmail.com
 * @date: 2021/1/10 16:48
 *
 * to-be tested!
 */
@Component
@Slf4j
public class HttpUtils {

    @Value("${wx.appId}")
    private String appId;

    @Value("${wx.appSecret}")
    private String appSecret;

    /**
     *  获取用户的openid 如果有问题 返回null
     * @param code 前端传回的code
     * @return openid 用户的唯一标识
     * @throws IOException
     */
    public String getOpenId(String code) throws IOException {

        if (code == null) {
            log.error("httputils: code 为空 parms={}", code);
            throw new RuntimeException("wx.login() code为空");
        }
        String url = "https://api.weixin.qq.com/sns/jscode2session?" +
                "appid=" + appId +
                "&secret=" + appSecret +
                "&js_code=" + code +
                "&grant_type=authorization_code";
        URL getUrl = new URL(url);

        HttpURLConnection httpURLConnection = (HttpURLConnection) getUrl.openConnection();

        //6s 超时
        httpURLConnection.setConnectTimeout(6000);
        httpURLConnection.setRequestMethod("GET");


        if (httpURLConnection.getResponseCode() == HttpStatus.SC_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
//            JSONObject jsonObject = JSON.parseObject(sb.toString());
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(sb.toString(), JsonObject.class);
            String openid = null;
            if (jsonObject.has("openid")) {
                openid = jsonObject.get("openid").toString();
            } else {
                log.error("errmsg={}",jsonObject.toString());
            }
            openid = openid.replace("\"","");
            return openid;
        } else {
            System.out.println(httpURLConnection.getResponseCode());
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            log.error("connectInfo = {}", sb.toString());
            return null;
        }
    }


}
