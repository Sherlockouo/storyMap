package com.storymap.controller.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.storymap.entity.Admin;
import com.storymap.entity.UserEntity;
import com.storymap.service.AdminService;
import com.storymap.service.MyUserDetailService;
import com.storymap.util.common.JwtTokenUtil;
import com.storymap.util.common.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Random;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    MyUserDetailService myUserDetailService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    @ApiOperation("登录")
    public R login(String username,String passwd){
        QueryWrapper<Admin> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("username", username);
        Admin one = adminService.getOne(objectQueryWrapper);
        UserDetails userDetails = myUserDetailService.loadUserByname(one.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        token = "Bearer " + token;
        return R.success().put("token", token).put("userinfo", one);
    }

    @PostMapping("/register")
    @Transactional(propagation = Propagation.REQUIRED)
    @ApiOperation("注册 => 微信授权 =>填写邮箱 =>注册成功 =>邮箱会发给他生成的验证码")
    @ResponseBody
    public R register(@RequestParam(value = "username",required = true)String username,
                      @RequestParam(value = "password",required = true)String password,
                      @RequestParam(value = "repassword",required = true)String repassword) throws IOException {
        if(username==null||username.equals("")){
            return R.error(501,"用户名不能为空");
        }
        if(password==null||password.equals("")){
            return R.error(502,"用户名不能为空");
        }
        if(password!=repassword){
            return R.error(503,"两次输入的密码不一致");
        }

        QueryWrapper<Admin> user = new QueryWrapper<>();
        user.eq("username", username);
        Admin one1 = adminService.getOne(user);
        if (one1 != null) {
            return R.error("您已经注册过了，请登录！");
        } else {

            Admin userEntity = new Admin();
            userEntity.setUsername(username);

            userEntity.setPassword(bCryptPasswordEncoder.encode(password));
            adminService.save(userEntity);
        }
        Admin userinfo = adminService.getOne(user);
        UserDetails userDetails = myUserDetailService.loadAdminByname(username);
        String token = jwtTokenUtil.generateToken(userDetails);
        token = "Bearer " + token;
        return R.success().put("token", token).put("userinfo", userinfo);
    }

}
