package com.storymap.util.common;

import com.storymap.entity.Message;
import com.storymap.entity.ToMessage;
import com.storymap.service.MsgService;
import com.storymap.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class WebSocketUtil {
    public static final Map<Long, Session> ONLINE_USER_SESSIONS = new ConcurrentHashMap<>();

    @Autowired
    private MsgService msgService;

    @Autowired
    private UserService userService;

    public static WebSocketUtil webSocketUtil;

    @PostConstruct // 初始化
    public void init() {
        webSocketUtil = this;
        webSocketUtil.msgService = this.msgService;
        webSocketUtil.userService = this.userService;
    }

    public static boolean doesExist(Long userid){
        return webSocketUtil.userService.getById(userid)!=null;
    }


    // 单用户推送
    public static void sendMessage(Session session, String message) {
        log.info("msg was sending!");
        if (session == null) {
            return;
        }
        RemoteEndpoint.Async async = session.getAsyncRemote();
        if (async == null) {
            return;
        }
        async.sendText(message);
        log.info("msg was send successfully!");
    }

    // 单用户推送
    public static void sendMessageSingle(ToMessage msg) {
        Message message = new Message();
        BeanUtils.copyProperties(msg, message);
        webSocketUtil.msgService.save(message);
//
//        sendMessage(ONLINE_USER_SESSIONS.get(msg.getSenduserid()), msg.getSendtext());

        log.info("session2 : {}", ONLINE_USER_SESSIONS.get(msg.getReciveuserid()));
        sendMessage(ONLINE_USER_SESSIONS.get(msg.getReciveuserid()), msg.getSendtext());
    }

    // 全用户推送
    public static void sendMessageAll(String message) {
        ONLINE_USER_SESSIONS.forEach((sessionId, session) -> {
            sendMessage(session, message);
        });
    }
}
