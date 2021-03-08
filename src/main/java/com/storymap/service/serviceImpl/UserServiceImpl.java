package com.storymap.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.storymap.dao.UserDao;
import com.storymap.entity.UserEntity;
import com.storymap.service.UserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {
}
