package com.storymap;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.storymap.util.common.Constant;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

@Slf4j
@RunWith(SpringRunner.class)
public class UtilTest {
    @Test
    public void httpTest(){
        String url = "https://apis.map.qq.com/ws/geocoder/v1/?address=成都市西华大学&key="+ Constant.MAPKEY+"";
        String s = HttpUtil.get(url, CharsetUtil.CHARSET_UTF_8);
        System.out.println(s);
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(s, JsonObject.class);
        Number longtitude;
        Number latitude;

        if (jsonObject.has("result")) {
            jsonObject = (JsonObject) jsonObject.get("result");
            if(jsonObject.has("location")) {
                jsonObject = (JsonObject) jsonObject.get("location");
                latitude = Double.parseDouble(jsonObject.get("lat").toString());
                longtitude = Double.parseDouble(jsonObject.get("lng").toString());
                log.info("latitude:{},longtitude:{}", latitude, longtitude);
            }
        } else {
            log.error("errmsg={}",jsonObject.toString());
        }
    }
}
