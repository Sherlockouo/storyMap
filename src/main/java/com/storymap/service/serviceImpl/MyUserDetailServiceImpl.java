package com.storymap.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.storymap.entity.UserEntity;
import com.storymap.service.FileService;
import com.storymap.service.MyUserDetailService;
import com.storymap.service.UserService;
import com.storymap.util.common.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: desc
 * @author: wdf
 * @email: wdf.coder@gmail.com
 * @date: 2021/1/9 15:31
 */
@Service("myUserDetailService")
public class MyUserDetailServiceImpl implements MyUserDetailService, UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<UserEntity> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("username", username);

        UserEntity one = userService.getOne(objectQueryWrapper);
        if(one==null)
        {
            return null;
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(Constant.LOGIN));
      return new User(one.getUsername(),one.getPassword() ,authorities);
    }

    @Override
    public UserDetails loadUserByname(String username) throws UsernameNotFoundException {
        QueryWrapper<UserEntity> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("username", username);

        UserEntity one = userService.getOne(objectQueryWrapper);
        if(one==null)
        {
            return null;
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(Constant.LOGIN));
        return new User(one.getUsername(),one.getPassword() ,authorities);
    }

}
