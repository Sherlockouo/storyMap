package com.storymap.controller.common;



//import com.alibaba.fastjson.JSON;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.storymap.entity.Message;
import com.storymap.entity.ToMessage;
import com.storymap.entity.UserEntity;
import com.storymap.service.UserService;
import com.storymap.util.common.WebSocketUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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



    /**
     * 连接成功响应
     */
    @OnOpen
    public void openSession(@PathParam("userId") @PathVariable Long userId, Session session) {

        if (!WebSocketUtil.doesExist(userId)) {
            log.info("客户端: [" + userId + "] : 用户不存在！");
            return ;
        }

        WebSocketUtil.ONLINE_USER_SESSIONS.put(userId, session);

        log.info("客户端: [" + userId + "] : 连接成功！");
        log.info("客户端: 在线总人数: {}",WebSocketUtil.ONLINE_USER_SESSIONS.size());
//        WebSocketUtil.sendMessageAll("客户端: [" + userId + "] : 连接成功！");
    }
    
    /**
     * 收到消息响应
     */
    @OnMessage
    public void onMessage(@PathParam("userId") @PathVariable Long userId, String message) {

            log.info("服务器收到：[" +userId + "] : {}" + message);

            JSONObject jsonObject = JSONUtil.parseObj(message);
            ToMessage to = jsonObject.toBean(ToMessage.class);

            log.info("msg {}",to);
            WebSocketUtil.sendMessageSingle(to);
    }

    /**
     * 连接关闭响应
     */
    @OnClose
    public void onClose(@PathParam("userId") @PathVariable Long userId, Session session) throws IOException {
        //当前的Session 移除
        WebSocketUtil.ONLINE_USER_SESSIONS.remove(userId);

        log.info("[" + userId + "] :     断开连接！");
        //并且通知其他人当前用户已经断开连接了
//        WebSocketUtil.sendMessageAll("[" +userId + "] : 断开连接！");
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
