package com.storymap.controller.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.storymap.entity.Collect;
import com.storymap.entity.Follow;
import com.storymap.entity.Like;
import com.storymap.entity.UserEntity;
import com.storymap.service.FollowService;
import com.storymap.service.LikeService;
import com.storymap.service.UserService;
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
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/follow")
public class FollowController {
    @Autowired
    FollowService followService;

    @Autowired
    UserService userService;

    @Autowired
    AuthUtil authUtil;
    @PostMapping("/dofollow")
    @ApiOperation("userid follow tofollowid")
    @RolesAllowed({Constant.LOGIN})
    public R like(Long tofollow){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity loginUser = authUtil.getLoginUser(authentication);
        QueryWrapper<Follow> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("userid",tofollow);
        objectQueryWrapper.eq("followerid",loginUser.getId());
        Follow one = followService.getOne(objectQueryWrapper);
        if(one!=null){
            one.setStatus(!one.getStatus());
            followService.updateById(one);
            return R.success("取消关注成功");
        }else {
            Follow follow = new Follow();
            follow.setFollowerid(loginUser.getId());
            follow.setUserid(tofollow);
            follow.setStatus(Boolean.TRUE);
            followService.saveOrUpdate(follow);
            return R.success("关注成功");
        }
    }

    @GetMapping("/didFollow")
    @RolesAllowed({Constant.LOGIN})
    public R didLike(Long userId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity loginUser = authUtil.getLoginUser(authentication);

        QueryWrapper<Follow> objectQueryWrapper1 = new QueryWrapper<>();
        objectQueryWrapper1.eq("userid",userId);
        objectQueryWrapper1.eq("status",Boolean.TRUE);
        objectQueryWrapper1.eq("followerid",loginUser.getId());
        Follow one = followService.getOne(objectQueryWrapper1);
        if(one==null){
            return R.success().put("data",false);
        }else{
            return R.success().put("data",true);
        }
    }


    @GetMapping("/list")
    @RolesAllowed({Constant.LOGIN})
    public R likelist(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity loginUser = authUtil.getLoginUser(authentication);
        QueryWrapper<Follow> followlist = new QueryWrapper<>();
        followlist.eq("followerid",loginUser.getId());
        followlist.eq("status",Boolean.TRUE);
        List<Follow> list = followService.list(followlist);
        List<Follow> collect = list.stream().filter(b -> {
            b.setUserEntity(userService.getById(b.getUserid()));
            return true;
        }).collect(Collectors.toList());
        return R.success().put("data",collect);
    }
}
