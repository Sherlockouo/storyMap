package com.storymap.controller.common;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.storymap.entity.Collect;
import com.storymap.entity.UserEntity;
import com.storymap.service.CollectService;
import com.storymap.service.LikeService;
import com.storymap.util.common.AuthUtil;
import com.storymap.util.common.Constant;
import com.storymap.util.common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/collect")
public class CollectController {
    @Autowired
    CollectService collectService;

    @Autowired
    AuthUtil authUtil;

    @PostMapping("/docollect")
    @RolesAllowed({Constant.LOGIN})
    public R like(Long posterId,Long userid){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity loginUser = authUtil.getLoginUser(authentication);
        QueryWrapper<Collect> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("posterid",posterId);
        objectQueryWrapper.eq("userid",loginUser.getId());
        objectQueryWrapper.eq("collectuserid",userid);
        Collect one = collectService.getOne(objectQueryWrapper);
        if(one!=null){
            one.setStatus(!one.getStatus());
            collectService.saveOrUpdate(one);
            return R.success("取消收藏成功");
        }
        Collect collect = new Collect();
        collect.setPosterid(posterId);
        collect.setUserid(loginUser.getId());
        collect.setCollectuserid(userid);
        collect.setStatus(Boolean.TRUE);
        collectService.saveOrUpdate(collect);
        return R.success("收藏成功");
    }

    @GetMapping("/list")
    @RolesAllowed({Constant.LOGIN})
    public R likelist(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity loginUser = authUtil.getLoginUser(authentication);
        QueryWrapper<Collect> collectlist = new QueryWrapper<>();
        collectlist.eq("userid",loginUser);
        collectlist.eq("status",Boolean.TRUE);
        collectService.list(collectlist);
        return R.success();
    }
}
