package com.storymap.controller.common;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.storymap.entity.Follow;
import com.storymap.entity.Like;
import com.storymap.entity.UserEntity;
import com.storymap.service.LikeService;
import com.storymap.service.PosterService;
import com.storymap.service.UserService;
import com.storymap.util.common.AuthUtil;
import com.storymap.util.common.Constant;
import com.storymap.util.common.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/like")
@RestController
public class LikeController {
    @Autowired
    LikeService likeService;

    @Autowired
    UserService userService;

    @Autowired
    PosterService posterService;

    @Autowired
    AuthUtil authUtil;
    @PostMapping("/dolike")
    @ApiOperation("userid follow tofollowid")
    @RolesAllowed({Constant.LOGIN})
    public R like(Long posterid,Long tolike){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity loginUser = authUtil.getLoginUser(authentication);
//        if(loginUser.getId()==tolike){
//            return R.error(400,"自己不能给自己点赞。");
//        }
        QueryWrapper<Like> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.and(
                Wrapper->Wrapper.eq("userid",tolike)
                                .eq("likeuserid",loginUser.getId())
                                .eq("posterid",posterid)
        );


        Like one = likeService.getOne(objectQueryWrapper);
        if(one!=null){
            one.setStatus(!one.getStatus());
            likeService.updateById(one);
            return R.success("点赞成功");
        }
        Like like = new Like();
        like.setPosterid(posterid);
        like.setLikeuserid(loginUser.getId());
        like.setUserid(tolike);
        like.setStatus(Boolean.TRUE);
        likeService.saveOrUpdate(like);
        return R.success("点赞成功");
    }

    @GetMapping("/didLike")
    @RolesAllowed({Constant.LOGIN})
    public R didLike(Long posterId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity loginUser = authUtil.getLoginUser(authentication);

        QueryWrapper<Like> objectQueryWrapper1 = new QueryWrapper<>();
        objectQueryWrapper1.eq("posterid",posterId);
        objectQueryWrapper1.eq("status",1);
        objectQueryWrapper1.eq("likeuserid",loginUser.getId());
        Like one = likeService.getOne(objectQueryWrapper1);
        if(one==null){
            return R.success().put("data",false);
        }else{
            return R.success().put("data",true);
        }
    }

    @GetMapping("/list")
    @RolesAllowed({Constant.LOGIN})
    @ApiOperation("获取喜欢我文章的list")
    public R likelist(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity loginUser = authUtil.getLoginUser(authentication);
        QueryWrapper<Like> followlist = new QueryWrapper<>();
        followlist.eq("userid",loginUser.getId());
        followlist.eq("status",Boolean.TRUE);
        List<Like> list = likeService.list(followlist);
        List<Like> collect = list.stream().filter(b -> {
            b.setPoster(posterService.getById(b.getPosterid()));
            b.setUserEntity(userService.getById(b.getLikeuserid()));
            return true;
        }).collect(Collectors.toList());
        return R.success().put("data",collect);
    }
}
