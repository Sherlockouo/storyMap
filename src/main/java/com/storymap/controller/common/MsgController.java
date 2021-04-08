package com.storymap.controller.common;

import com.storymap.service.MsgSendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Api(tags = "消息接口")
@RequiredArgsConstructor
@RestController
@RequestMapping("/msg")
public class MsgController {

    private final MsgSendService msgSendService;


    @ApiOperation("发送消息")
    @GetMapping("/send")
    public String send(String msg) {
        Map<String,Object> map=new HashMap<>();
        map.put("msg",msg);
        map.put("date", LocalDateTime.now().toString());
        int size = msgSendService.sendMsg(map);
        return "sucess " + size;
    }


}
