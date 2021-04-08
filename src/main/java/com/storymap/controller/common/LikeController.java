package com.storymap.controller.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.storymap.entity.Follow;
import com.storymap.entity.Like;
import com.storymap.entity.UserEntity;
import com.storymap.service.LikeService;
import com.storymap.util.common.AuthUtil;
import com.storymap.util.common.Constant;
import com.storymap.util.common.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RequestMapping("/like")
@RestController
public class LikeController {
    @Autowired
    LikeService likeService;

    @Autowired
    AuthUtil authUtil;
    @PostMapping("/dolike")
    @ApiOperation("userid follow tofollowid")
    @RolesAllowed({Constant.LOGIN})
    public R like(Long posterid,Long tolike){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity loginUser = authUtil.getLoginUser(authentication);
        QueryWrapper<Like> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("likeuserid",tolike);
        objectQueryWrapper.eq("userid",loginUser.getId());
        objectQueryWrapper.eq("posterid",posterid);
        Like one = likeService.getOne(objectQueryWrapper);
        if(one!=null){
            one.setStatus(!one.getStatus());
            likeService.saveOrUpdate(one);
            return R.success("点赞成功");
        }
        Like like = new Like();
        like.setPosterId(posterid);
        like.setLikeuserid(tolike);
        like.setUserid(loginUser.getId());
        like.setStatus(Boolean.TRUE);
        likeService.saveOrUpdate(like);
        return R.success("点赞成功");
    }

    @GetMapping("/didLike")
    public R didLike(){
        return R.success();
    }

    @GetMapping("/list")
    @RolesAllowed({Constant.LOGIN})
    public R likelist(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity loginUser = authUtil.getLoginUser(authentication);
        QueryWrapper<Like> followlist = new QueryWrapper<>();
        followlist.eq("userid",loginUser);
        followlist.eq("status",Boolean.TRUE);
        likeService.list(followlist);
        return R.success();
    }
}
