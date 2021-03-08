package com.storymap;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.storymap.util.common.ExtraUtil;
import com.storymap.util.common.R;
import com.storymap.util.common.ValidateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@SpringBootTest
class StorymapApplicationTests {

    @Autowired
    ValidateUtil validateUtil;

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    ExtraUtil extraUtil;

    @Test
    void contextLoads() {
    }

    @Test
    void picExtra() throws JpegProcessingException, IOException {
        File jpegFile = new File("/Users/wdf/Desktop/fireworks.jpg");
        Metadata metadata = JpegMetadataReader.readMetadata(jpegFile);
        Map<String,String> mp=new HashMap<>();
        for(Directory directory : metadata.getDirectories()){
            for(Tag tag : directory.getTags()){
                mp.put(tag.getTagName(),tag.getDescription());
            }
        }
        System.out.println(mp.get("GPS Longitude Ref"));
        System.out.println(mp.get("GPS Longitude"));
        System.out.println(mp.get("GPS Latitude Ref"));
        System.out.println(mp.get("GPS Latitude"));
        System.out.println(mp.get("Altitude Ref"));
        System.out.println(mp.get("Altitude"));
        System.out.println(mp.get("GPS Date Stamp"));
        System.out.println(mp.get("Make"));
        System.out.println(mp.get("Model"));

    }

    @Test
    void getfile() throws JpegProcessingException, IOException {
        File file = new File("/Users/wdf/Desktop/view.jpg");

        Map<String, String> info = extraUtil.getInfo(file);
        for(Map.Entry<String,String> entry:info.entrySet()){
            System.out.println("key: "+entry.getKey()+" value: "+entry.getValue());
        }
    }

    @Test
    void sendMail(){

        String email = "wdf.coder@gmail.com";


        StringBuffer stringBuffer = new StringBuffer();
//        Random random = new Random();
//        for (int i = 0; i < 6; i++)
//            stringBuffer.append(random.nextInt(10));
        for (int i = 0; i < 6; i++)
            stringBuffer.append(8);
        // 纯文本邮件对象
        SimpleMailMessage message = new SimpleMailMessage();

        String title = "好";

        String content = "这不是垃圾邮件" +
                "他说这个要长一点才不会倍作为垃圾" +
                " 验证码为："+stringBuffer.toString();

        message.setFrom("wudingfeng" + "<" + "wudingfeng@sherlockouo.com" + ">");
        message.setTo(email);
        message.setSubject(title);
        message.setText(content);

        try {
            mailSender.send(message);
            log.info("通知邮件发送成功!");
        } catch (Exception e) {
            log.error("发送通知邮件时发生异常！", e);
        }

    }
}
