package com.storymap.controller.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.storymap.entity.UserEntity;
import com.storymap.service.MyUserDetailService;
import com.storymap.service.UserService;
import com.storymap.util.common.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.io.IOException;
import java.util.Random;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    MyUserDetailService myUserDetailService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    HttpUtl httpUtl;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    CodeUtil codeUtil;

    @PostMapping("/refresh")
    @ApiOperation("更新token")
    public R refreshToken(){

        return R.success();
    }

    @PostMapping("/login")
    @ApiOperation("登录 发获取的code")
    public R login(String wxcode) throws IOException {
        String openid = httpUtl.getOpenId(wxcode);
//        /getopenid/

        QueryWrapper<UserEntity> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("openid",openid);
        UserEntity one = userService.getOne(objectQueryWrapper);
        UserDetails userDetails = myUserDetailService.loadUserByname(one.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        token = "Bearer "+token;
        return R.success().put("token",token).put("userinfo",one);
    }

    @PostMapping("/register")
    @Transactional(propagation = Propagation.REQUIRED)
    @ApiOperation("注册 => 微信授权 =>填写邮箱 =>注册成功 =>邮箱会发给他生成的验证码")
    public R register(String wxcode, String email) throws IOException {
        String openId = httpUtl.getOpenId(wxcode);
        UserEntity userEntity = new UserEntity();
        userEntity.setOpenid(openId);
        userEntity.setUsername(email);

        StringBuffer pass = new StringBuffer();
        String num = "0123456789";
        String english = "qwertyuiopasdfghjklzxcvbnm";
        String englishBig = "QWERTYUIOPASDFGHJKLZXCVBNM";
        String symbol = "!@#$%^&*_+-{}<>.*";
        String stringSum = num + english + englishBig + symbol;
        int length = stringSum.length();
        //定义密码长度,写死8
        int passwordLength = 8;
        for (int i = 0; i < passwordLength; i++) {
            Random random = new Random();
            int a = random.nextInt(length + 1);
            char one = stringSum.charAt(a);
            pass.append(one);
        }
        codeUtil.sendEmail(email,pass.toString());

        userEntity.setPassword(bCryptPasswordEncoder.encode(pass));
        userService.save(userEntity);

        UserDetails userDetails = myUserDetailService.loadUserByname(email);
        String token = jwtTokenUtil.generateToken(userDetails);
        token = "Bearer "+token;
        return R.success().put("token",token).put("userinfo",userEntity);
    }

    @PutMapping("/update")
    @ApiOperation("更新头像 还没写")
    @RolesAllowed({Constant.LOGIN})
    public R update(String url){

        return R.success();
    }
}
