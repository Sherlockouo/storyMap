package com.storymap.controller.common;



import com.alibaba.fastjson.JSON;
import com.storymap.entity.Message;
import com.storymap.entity.UserEntity;
import com.storymap.service.UserService;
import com.storymap.util.common.WebSocketUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value = "/chat/{userId}")
@Component
@Slf4j
public class WebSocketController {

    @Autowired
    UserService userService;

    @Autowired
    WebSocketUtil webSocketUtil;


    /**
     * 连接成功响应
     */
    @OnOpen
    public void openSession(@PathParam("userId") @PathVariable Integer userId, Session session) {

        if ( userId == null||userId==0) {
            log.info("客户端: [" + userId + "] : 用户不存在！");
            return;
        }
        WebSocketUtil.ONLINE_USER_SESSIONS.put(userId, session);
        log.info("客户端: [" + userId + "] : 连接成功！");

    }
    
    /**
     * 收到消息响应
     */
    @OnMessage
    public void onMessage(@PathParam("userId") @PathVariable Integer userId, String message) {


        log.info("服务器收到：[" +userId + "] : {}" + message);
        Message msg = JSON.parseObject(message, Message.class);
        log.info("msg {}",msg);
        webSocketUtil.sendMessageSingle(msg);
        log.info("msg {}",msg);
    }

    /**
     * 连接关闭响应
     */
    @OnClose
    public void onClose(@PathParam("userId") @PathVariable Integer userId, Session session) throws IOException {
        //当前的Session 移除
        webSocketUtil.ONLINE_USER_SESSIONS.remove(userId);

        log.info("[" + userId + "] :     断开连接！");
        //并且通知其他人当前用户已经断开连接了
        //webSocketUtil.sendMessageAll("[" +userId + "] : 断开连接！");
        session.close();
    }

    /**
     * 连接异常响应
     */
    @OnError
    public void onError(Session session, Throwable throwable) throws IOException {
        log.error("连接异常响应!!");
        session.close();
    }
}
