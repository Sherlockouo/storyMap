package com.storymap.controller.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.storymap.config.KaptchaConfig;
import com.storymap.entity.Message;
import com.storymap.entity.UserEntity;
import com.storymap.service.MsgService;
import com.storymap.service.UserService;
import com.storymap.util.common.AuthUtil;
import com.storymap.util.common.PageUtils;
import com.storymap.util.common.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/chatlog")
@RestController
@Api("获取聊天记录")
public class MsgController {
    @Autowired
    MsgService msgService;

    @Autowired
    UserService userService;

    @Autowired
    AuthUtil authUtil;
    /**
     * 1. 获取与所有人的聊天记录列表
     * 2. 获取与单独一个人的聊天记录
     */

    @GetMapping("/all")
    @ApiOperation("获取当前用户的聊天list")
    public R allChatLog(Integer pageNum,Integer pageSize){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity loginUser = authUtil.getLoginUser(authentication);
        QueryWrapper<Message> msgwrapper = new QueryWrapper<>();
        msgwrapper.and(
                Wrapper-> Wrapper.eq("senduserid",loginUser.getId())
                                 .or()
                                 .eq("reciveuserid",loginUser.getId())
        ).orderByDesc("sendtime");
//        msgwrapper.and(
//                Wrapper-> Wrapper.eq("senduserid",loginUser.getId())
//                        .or()
//                        .eq("reciveuserid",loginUser.getId())
//        );
        Page<Message> msgpage = new Page<>(pageNum, pageSize);
        Page<Message> page = msgService.page(msgpage,msgwrapper);
        Map<Long,Object> hashMap = new HashMap<>();
        List<Message> records = page.getRecords();



        for(int i=0;i<records.size();i++){
//            Message message = records.get(i);
            //如果消息是自己发的
            if(loginUser.getId()!=records.get(i).getReciveuserid()&&hashMap.get(records.get(i).getSenduserid())==null) {
                UserEntity one = userService.getById(records.get(i).getReciveuserid());
                QueryWrapper<Message> rqw = new QueryWrapper<>();
                rqw.eq("senduserid",loginUser.getId());
                rqw.eq("reciveuserid",one.getId());
                rqw.orderByDesc("sendtime");
                List<Message> list = msgService.list(rqw);
                Message message = list.get(0);
                message.setSenduser(one);
                hashMap.put(records.get(i).getReciveuserid(), list.get(0));
            }
            //如果消息是对面发的
            if(loginUser.getId()!=records.get(i).getSenduserid()&&hashMap.get(records.get(i).getSenduserid())==null) {
                UserEntity one = userService.getById(records.get(i).getSenduserid());
                QueryWrapper<Message> rqw = new QueryWrapper<>();
                rqw.eq("senduserid",one.getId());
                rqw.eq("reciveuserid",loginUser.getId());
                rqw.orderByDesc("sendtime");
                List<Message> list = msgService.list(rqw);
                Message message = list.get(0);
                message.setSenduser(one);
                hashMap.put(records.get(i).getSenduserid(), message);
            }
        }
//
//        Map<Integer,Object> hhMap = new HashMap<>();
//
//        for(Map.Entry<Long,Integer> entry:hashMap.entrySet()){
//            UserEntity one = userService.getById(entry.getKey());
//            QueryWrapper<Message> wrapper = new QueryWrapper<>();
//            wrapper.and(
//                    Wrapper-> Wrapper.eq("senduserid",loginUser.getId())
//                            .or()
//                            .eq("reciveuserid",loginUser.getId())
//            ).orderByAsc("sendtime");
//
//            List<Message> list = msgService.list(wrapper);
//            Message message = list.get(0);
//            message.setSenduser(one);
//            hhMap.put(entry.getValue(),message);
//        }

        return  R.success().put("data",hashMap);
    }


    @GetMapping("/one")
    @ApiOperation("获取当前用户与touserid 的聊天记录")
    public R singleChatLog(Long toUserId,Integer pageNum,Integer pageSize){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity loginUser = authUtil.getLoginUser(authentication);
        QueryWrapper<Message> msgwrapper = new QueryWrapper<>();
        msgwrapper.and(
                Wrapper-> Wrapper.eq("senduserid",loginUser.getId())
                        .eq("reciveuserid",toUserId)
                        .or()
                        .eq("senduserid",toUserId)
                        .eq("reciveuserid",loginUser.getId())
        ).orderByAsc("sendtime");

        Page<Message> msgpage = new Page<>(pageNum, pageSize);
        Page<Message> page = msgService.page(msgpage,msgwrapper);

        PageUtils pageUtils = new PageUtils(page);

        return  R.success().put("data",pageUtils);
    }

}
