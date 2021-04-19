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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    public R allChatLog(Long toUserId,Integer pageNum,Integer pageSize){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity loginUser = authUtil.getLoginUser(authentication);
        QueryWrapper<Message> msgwrapper = new QueryWrapper<>();
        msgwrapper.and(
                Wrapper-> Wrapper.eq("senduserid",loginUser.getId())
                                 .eq("reciveuserid",toUserId)
                                 .eq("senduserid",toUserId)
                                 .eq("reciveuserid",loginUser.getId())
        ).orderByDesc("sendtime");
        Page<Message> msgpage = new Page<>(pageNum, pageSize);
        Page<Message> page = msgService.page(msgpage,msgwrapper);
        Map<Integer,Long> hashMap = new HashMap<>();
        List<Message> records = page.getRecords();
        for(int i=0;i<records.size();i++){

            hashMap.put(i,records.get(i).getReciveuserid());
            hashMap.put(i,records.get(i).getSenduserid());
        }
        Map<Integer,Object> hhMap = new HashMap<>();
        for(Map.Entry<Integer,Long> entry:hashMap.entrySet()){
            UserEntity one = userService.getById(entry.getValue());
            hhMap.put(entry.getKey(),one);
        }

        return  R.success().put("data",hhMap);
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
                        .eq("senduserid",toUserId)
                        .eq("reciveuserid",loginUser.getId())
        ).orderByDesc("sendtime");
        Page<Message> msgpage = new Page<>(pageNum, pageSize);
        Page<Message> page = msgService.page(msgpage,msgwrapper);

        PageUtils pageUtils = new PageUtils(page);

        return  R.success().put("data",pageUtils);
    }

}
