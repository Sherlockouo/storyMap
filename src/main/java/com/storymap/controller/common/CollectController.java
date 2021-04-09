package com.storymap.controller.common;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.storymap.entity.Collect;
import com.storymap.entity.Like;
import com.storymap.entity.UserEntity;
import com.storymap.service.CollectService;
import com.storymap.service.LikeService;
import com.storymap.service.PosterService;
import com.storymap.service.UserService;
import com.storymap.util.common.AuthUtil;
import com.storymap.util.common.Constant;
import com.storymap.util.common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/collect")
public class CollectController {
    @Autowired
    CollectService collectService;

    @Autowired
    PosterService posterService;

    @Autowired
    UserService userService;

    @Autowired
    AuthUtil authUtil;

    @PostMapping("/docollect")
    @RolesAllowed({Constant.LOGIN})
    public R like(Long posterId,Long userid){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity loginUser = authUtil.getLoginUser(authentication);
        QueryWrapper<Collect> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.and(
                Wrapper->Wrapper.eq("posterid",posterId)
                        .eq("userid",userid)
                        .eq("collectuserid",loginUser.getId())
        );
        Collect one = collectService.getOne(objectQueryWrapper);
        if(one!=null){
            one.setCollectstatus(!one.getCollectstatus());
            collectService.updateById(one);
            return R.success("取消收藏成功");
        }
        Collect collect = new Collect();
        collect.setPosterid(posterId);
        collect.setUserid(userid);
        collect.setCollectuserid(loginUser.getId());
        collect.setCollectstatus(Boolean.TRUE);
        collectService.saveOrUpdate(collect);
        return R.success("收藏成功");
    }

    @GetMapping("/didCollect")
    public R didLike(Long posterId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity loginUser = authUtil.getLoginUser(authentication);

        QueryWrapper<Collect> objectQueryWrapper1 = new QueryWrapper<>();
        objectQueryWrapper1.eq("posterid",posterId);
        objectQueryWrapper1.eq("collectstatus",1);
        objectQueryWrapper1.eq("collectuserid",loginUser.getId());
        Collect one = collectService.getOne(objectQueryWrapper1);

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
        QueryWrapper<Collect> collectlist = new QueryWrapper<>();
        collectlist.eq("collectuserid",loginUser.getId());
        collectlist.eq("collectstatus",Boolean.TRUE);
        List<Collect> list = collectService.list(collectlist);
        List<Collect> collect = list.stream().filter(b -> {
            b.setUserEntity(userService.getById(b.getUserid()));
            b.setPoster(posterService.getById(b.getPosterid()));
            return true;
        }).collect(Collectors.toList());
        return R.success().put("data",collect);
    }
}
