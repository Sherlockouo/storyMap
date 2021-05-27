package com.storymap.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.storymap.dao.AdminDao;
import com.storymap.dao.UserDao;
import com.storymap.entity.Admin;
import com.storymap.entity.UserEntity;
import com.storymap.service.AdminService;
import com.storymap.service.UserService;
import org.springframework.stereotype.Service;

@Service("adminService")
public class AdminServiceImpl extends ServiceImpl<AdminDao, Admin> implements AdminService {
}
