package com.storymap.controller.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.storymap.entity.Collect;
import com.storymap.entity.Follow;
import com.storymap.entity.UserEntity;
import com.storymap.service.FollowService;
import com.storymap.service.LikeService;
import com.storymap.util.common.AuthUtil;
import com.storymap.util.common.Constant;
import com.storymap.util.common.R;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/follow")
public class FollowController {
    @Autowired
    FollowService followService;

    @Autowired
    AuthUtil authUtil;
    @PostMapping("/dofollow")
    @ApiOperation("userid follow tofollowid")
    @RolesAllowed({Constant.LOGIN})
    public R like(Long tofollow){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity loginUser = authUtil.getLoginUser(authentication);
        QueryWrapper<Follow> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("followerid",tofollow);
        objectQueryWrapper.eq("userid",loginUser.getId());
        Follow one = followService.getOne(objectQueryWrapper);
        if(one!=null){
            one.setStatus(!one.getStatus());
            followService.saveOrUpdate(one);
            return R.success("取消关注成功");
        }
        Follow follow = new Follow();
        follow.setFollowerid(tofollow);
        follow.setUserid(loginUser.getId());
        follow.setStatus(Boolean.TRUE);
        followService.saveOrUpdate(follow);
        return R.success("关注成功");
    }

    @GetMapping("/list")
    @RolesAllowed({Constant.LOGIN})
    public R likelist(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity loginUser = authUtil.getLoginUser(authentication);
        QueryWrapper<Follow> followlist = new QueryWrapper<>();
        followlist.eq("userid",loginUser);
        followlist.eq("status",Boolean.TRUE);
        followService.list(followlist);
        return R.success();
    }
}
