package com.storymap.controller.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.storymap.entity.UserEntity;
import com.storymap.service.MyUserDetailService;
import com.storymap.service.UserService;
import com.storymap.util.common.*;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Random;

@Slf4j
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

    @Autowired
    AuthUtil authUtil;

    @PostMapping("/refresh")
    @ApiOperation("更新token")
    public R refreshToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity loginUser = authUtil.getLoginUser(authentication);
        UserDetails userDetails = myUserDetailService.loadUserByname(loginUser.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        token = "Bearer " + token;
        return R.success().put("token", token);
    }

    @PostMapping("/login")
    @ApiOperation("登录 发获取的code")
    public R login(String wxcode) throws IOException {
        String openid = httpUtl.getOpenId(wxcode);
//        /getopenid/

        QueryWrapper<UserEntity> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("openid", openid);
        UserEntity one = userService.getOne(objectQueryWrapper);
        UserDetails userDetails = myUserDetailService.loadUserByname(one.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        token = "Bearer " + token;
        return R.success().put("token", token).put("userinfo", one);
    }

    @PostMapping("/register")
    @Transactional(propagation = Propagation.REQUIRED)
    @ApiOperation("注册 => 微信授权 =>填写邮箱 =>注册成功 =>邮箱会发给他生成的验证码")
    public R register(String wxcode,String avatarUrl,String nickname) throws IOException {
        String openId = httpUtl.getOpenId(wxcode);

        QueryWrapper<UserEntity> user = new QueryWrapper<>();
        user.eq("username", openId);
        UserEntity one1 = userService.getOne(user);
        log.info("one {}",one1);
        if (one1 != null) {
//            return R.error("您已经注册过了，请登录！");
        } else {

            UserEntity userEntity = new UserEntity();
            userEntity.setOpenid(openId);
            userEntity.setUsername(openId);
            userEntity.setAvatar(avatarUrl);
            userEntity.setNickname(nickname);
            StringBuffer pass = new StringBuffer();
            String num = "0123456789";
            String english = "qwertyuiopasdfghjklzxcvbnm";
            String englishBig = "QWERTYUIOPASDFGHJKLZXCVBNM";
            String symbol = "!@#$%^&*_+-{}<>.*";
            String stringSum = num + english + englishBig + symbol;
            int length = stringSum.length();
//        定义密码长度,写死8
            int passwordLength = 8;
            for (int i = 0; i < passwordLength; i++) {
                Random random = new Random();
                int a = random.nextInt(length + 1);
                char one = stringSum.charAt(a);
                pass.append(one);
            }
//        codeUtil.sendEmail(email,pass.toString());

            userEntity.setPassword(bCryptPasswordEncoder.encode(pass));
            userService.save(userEntity);
        }
        UserEntity userinfo = userService.getOne(user);
        UserDetails userDetails = myUserDetailService.loadUserByname(openId);
        String token = jwtTokenUtil.generateToken(userDetails);
        token = "Bearer " + token;
        return R.success().put("token", token).put("userinfo", userinfo);
    }

    @PutMapping("/updateBgimg")
    @ApiOperation("更新背景图片")
    @RolesAllowed({Constant.LOGIN})
    public R updateBgUrl(@NotNull String bgimg) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity loginUser = authUtil.getLoginUser(authentication);
        loginUser.setBgimg(bgimg);
        userService.updateById(loginUser);
        return R.success("更新成功");
    }
    @PutMapping("/updateAvatar")
    @ApiOperation("更新头像链接")
    @RolesAllowed({Constant.LOGIN})
    public R updateAvatar(@NotNull String avatar) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity loginUser = authUtil.getLoginUser(authentication);
        loginUser.setAvatar(avatar);
        userService.updateById(loginUser);
        return R.success("更新成功");
    }
    @PutMapping("/updateInfo")
    @ApiOperation("更新基本信息")
    @RolesAllowed({Constant.LOGIN})
    public R update(@NotNull String nickname,@NotNull String motto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity loginUser = authUtil.getLoginUser(authentication);
        loginUser.setNickname(nickname);
        loginUser.setMotto(motto);
        userService.updateById(loginUser);
        return R.success("更新成功");
    }

    @GetMapping("/getInfo/{userid}")
    @ApiOperation("获取用户信息")
    public R userinfo(@PathVariable Long userid){
        UserEntity byId = userService.getById(userid);
        return R.success().put("data",byId);
    }
}
