package com.storymap.controller.util;


import com.storymap.util.common.R;
import com.storymap.util.common.RedisUtil;
import com.storymap.util.common.ValidateUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @description: desc
 * @author: wdf
 * @email: wdf.coder@gmail.com
 * @date: 2021/1/12 16:28
 */
@Slf4j
@RestController
@RequestMapping("/code")
public class CodeController {

    @Value("${mail.from}")
    private String from;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ValidateUtil validateUtil;

//    @GetMapping("/getkaptcha")
////    @ApiOperation("获取图片验证码 还没写好")
//    public R sendKaptch() {
//        return R.success();
//    }
//
//    @PostMapping("checkkaptcha")
//    public R checkPic() {
//        return R.success();
//    }


    @PostMapping("/sendEmailCode")
    @ApiOperation("邮件发送验证码接口")
    public R emailCode(String email) {

        boolean phoneNumber = validateUtil.isEmail(email);
        if (!phoneNumber)
            return R.error(400, "手机号错误");

        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 6; i++)
            stringBuffer.append(random.nextInt(10));
        // 纯文本邮件对象
        SimpleMailMessage message = new SimpleMailMessage();

        String title = "微信小程序StoryMap验证码";

        String content = "验证码为："+stringBuffer.toString();

        message.setFrom("StoryMap" + "<" + from + ">");
        message.setTo(email);
        message.setSubject(title);
        message.setText(content);

        try {
            mailSender.send(message);
            log.info("通知邮件发送成功!");
        } catch (Exception e) {
            log.error("发送通知邮件时发生异常！", e);
        }


        redisUtil.set(email, stringBuffer.toString(), 300000);

        Map<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("status", 200);
        objectObjectHashMap.put("msg", "发送成功");

        return R.success(objectObjectHashMap);
    }

    @PostMapping("/checkEmail")
    @ApiOperation("邮件验证 check接口")
    public R checkEmailCode(String email, String smscode) {

        boolean phoneNumber = validateUtil.isEmail(email);
        if (!phoneNumber)
            return R.error(400, "邮箱错误");

        Map<String, Object> objectObjectHashMap = new HashMap<>();

        if (redisUtil.get(email) == null)
            return R.error(400, "请先获取验证码");

        if (redisUtil.get(email).equals(smscode)) {

            objectObjectHashMap.put("status", 200);
            objectObjectHashMap.put("msg", "验证码正确");
            // 一次性验证码
            redisUtil.delete(email);

            return R.success(objectObjectHashMap);
        } else {
            objectObjectHashMap.put("status", 400);
            objectObjectHashMap.put("msg", "验证码错误");
            return R.success(objectObjectHashMap);
        }
    }





}
