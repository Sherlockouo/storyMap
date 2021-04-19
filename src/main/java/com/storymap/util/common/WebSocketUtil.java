package com.storymap.util.common;

import com.storymap.entity.Message;
import com.storymap.service.MsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class WebSocketUtil {
    public static final Map<Integer, Session> ONLINE_USER_SESSIONS = new ConcurrentHashMap<>();



    // 单用户推送
    public static void sendMessage(Session session, String message) {

        if (session == null) { return; }
        RemoteEndpoint.Async async = session.getAsyncRemote();
        if (async == null) { return; }
        async.sendText(message);
    }

    // 单用户推送
    public static void sendMessageSingle(Message msg) {
        sendMessage(ONLINE_USER_SESSIONS.get(msg.getSenduserid()), msg.getSendtext());
        sendMessage(ONLINE_USER_SESSIONS.get(msg.getReciveuserid()), msg.getSendtext());
    }

    // 全用户推送
    public static void sendMessageAll(String message) {
        ONLINE_USER_SESSIONS.forEach((sessionId, session) -> {
            sendMessage(session, message);
        });
    }
}
