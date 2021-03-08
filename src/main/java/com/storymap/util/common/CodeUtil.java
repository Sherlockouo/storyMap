package com.storymap.util.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CodeUtil {
    @Value("${mail.from}")
    private String from;

    @Autowired
    JavaMailSender mailSender;

    public String sendSms(String tel){
        /**
         * to-do
         */
        return "";
    }

    public boolean sendEmail(String account,String pass){
        // 纯文本邮件对象
        SimpleMailMessage message = new SimpleMailMessage();

        String title = "微信小程序StoryMap 账号密码";

        String content = "账号为："+account+"\n" +
                "密码为："+pass;

        message.setFrom("StoryMap" + "<" + from + ">");
        message.setTo(account);
        message.setSubject(title);
        message.setText(content);

        try {
            mailSender.send(message);
            log.info("通知邮件发送成功!");
            return true;
        } catch (Exception e) {
            log.error("发送通知邮件时发生异常！", e);
            return false;
        }
    }
}
