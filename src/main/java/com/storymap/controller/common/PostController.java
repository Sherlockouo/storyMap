package com.storymap.controller.common;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.storymap.entity.Poster;
import com.storymap.entity.UserEntity;
import com.storymap.service.MyUserDetailService;
import com.storymap.service.PosterService;
import com.storymap.util.common.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/poster")
public class PostController {
    @Autowired
    PosterService posterService;

    @Autowired
    AuthUtil authUtil;

    @Autowired
    HttpUtl httpUtl;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    MyUserDetailService myUserDetailService;

    @PostMapping("/post")
    @ApiOperation("发poster")
    @RolesAllowed({Constant.LOGIN})
    public R post(Poster poster){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity loginUser = authUtil.getLoginUser(authentication);
        poster.setUserid(loginUser.getId());
        posterService.save(poster);
        return R.success();
    }

    @GetMapping("/self")
    @ApiOperation("获取自己的poster信息")
    @RolesAllowed({Constant.LOGIN})
    public R getself(String type, Integer pageNum,Integer pageSize){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity loginUser = authUtil.getLoginUser(authentication);
        QueryWrapper<Poster> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("userid",loginUser.getId());
        objectQueryWrapper.eq("type",type);
        Page<Poster> objectPage = new Page<>(pageNum,pageSize);
        Page<Poster> page = posterService.page(objectPage, objectQueryWrapper);
        PageUtils pageUtils = new PageUtils(page);
        return R.success().put("data",pageUtils);
    }

    @GetMapping("/all")
    @ApiOperation("获取所有的poster信息")
    public R getAll(Integer pageNum,Integer pageSize){
        QueryWrapper<Poster> objectQueryWrapper = new QueryWrapper<>();
        Page<Poster> objectPage = new Page<>(pageNum,pageSize);
        Page<Poster> page = posterService.page(objectPage, objectQueryWrapper);
        PageUtils pageUtils = new PageUtils(page);
        return R.success().put("data",pageUtils);
    }

    @GetMapping("/type")
    @ApiOperation("获取所有的类型为type的信息")
    public R getType(String type, Integer pageNum,Integer pageSize){
        QueryWrapper<Poster> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("type",type);
        Page<Poster> objectPage = new Page<>(pageNum,pageSize);
        Page<Poster> page = posterService.page(objectPage, objectQueryWrapper);
        PageUtils pageUtils = new PageUtils(page);
        return R.success().put("data",pageUtils);
    }

    @GetMapping("/local")
    @ApiOperation("获取本地poster信息")
//    @RolesAllowed({Constant.LOGIN})
    public R getLocal(@ApiParam("lat") Number lat,@ApiParam("lng") Number lng ,@ApiParam("type") String type, @ApiParam("pageNumber") Integer pageNum,@ApiParam("pageSize") Integer pageSize){
        QueryWrapper<Poster> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.and(
                Wrapper->Wrapper.between("latitude",lat.floatValue()-Constant.RANGE,lat.floatValue()+Constant.RANGE)
                                .between("longtitude",lng.floatValue()-Constant.RANGE,lng.floatValue()+Constant.RANGE)
                                .eq("type",type)
        );
        Page<Poster> objectPage = new Page<>(pageNum,pageSize);
        Page<Poster> page = posterService.page(objectPage, objectQueryWrapper);
        PageUtils pageUtils = new PageUtils(page);
        return R.success().put("data",pageUtils);
    }
    @GetMapping("/info")
    @ApiOperation("获取单个poster信息")
//    @RolesAllowed({Constant.LOGIN})
    public R getInfo(Long posterId){
        Poster byId = posterService.getById(posterId);
        return R.success().put("data",byId);
    }
}
