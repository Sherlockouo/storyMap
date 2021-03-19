package com.storymap.util.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.storymap.entity.UserEntity;
import com.storymap.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Console;

@Slf4j
@Component
public class AuthUtil {

    @Autowired
    UserService userService;

    public UserEntity getLoginUser(Authentication authentication){
        String usernanme = authentication.getName();

        QueryWrapper<UserEntity> objectQueryWrapper = new QueryWrapper<>();
        log.info("username={}",usernanme);
        objectQueryWrapper.eq("username",usernanme);
        UserEntity one = userService.getOne(objectQueryWrapper);
        return one;
    }
}
