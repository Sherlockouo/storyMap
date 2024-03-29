package com.storymap.controller.common;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.storymap.entity.Like;
import com.storymap.entity.Poster;
import com.storymap.entity.UserEntity;
import com.storymap.service.LikeService;
import com.storymap.service.MyUserDetailService;
import com.storymap.service.PosterService;
import com.storymap.service.UserService;
import com.storymap.util.common.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    UserService userService;

    @Autowired
    LikeService likeService;

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

    @GetMapping("/user/{userId}")
    @ApiOperation("获取自己的poster信息")
    @RolesAllowed({Constant.LOGIN})
    public R getuserpost(String type, Integer pageNum,Integer pageSize,@PathVariable Long userId){
        UserEntity loginUser = userService.getById(userId);
        QueryWrapper<Poster> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("userid",loginUser.getId());
        objectQueryWrapper.eq("type",type);
        Page<Poster> objectPage = new Page<>(pageNum,pageSize);
        Page<Poster> page = posterService.page(objectPage, objectQueryWrapper);
        List<Poster> records = page.getRecords();
        records.stream().filter(b->{
            b.setAvatar(userService.getById(b.getUserid()).getAvatar());
            b.setUsername(userService.getById(b.getUserid()).getNickname());
            QueryWrapper<Like> objectQueryWrapper1 = new QueryWrapper<>();
            objectQueryWrapper1.eq("posterid",b.getId());
            objectQueryWrapper1.eq("status",1);
            int count = likeService.count(objectQueryWrapper1);
            b.setLikes(count);
            return true;
        }).collect(Collectors.toList());
        PageUtils pageUtils = new PageUtils(records,page.getTotal(),page.getSize(),page.getCurrent());
        return R.success().put("data",pageUtils);
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
        List<Poster> records = page.getRecords();
        records.stream().filter(b->{
            b.setAvatar(userService.getById(b.getUserid()).getAvatar());
            b.setUsername(userService.getById(b.getUserid()).getNickname());
            QueryWrapper<Like> objectQueryWrapper1 = new QueryWrapper<>();
            objectQueryWrapper1.eq("posterid",b.getId());
            objectQueryWrapper1.eq("status",1);
            int count = likeService.count(objectQueryWrapper1);
            b.setLikes(count);
            return true;
        }).collect(Collectors.toList());
        PageUtils pageUtils = new PageUtils(records,page.getTotal(),page.getSize(),page.getCurrent());
        return R.success().put("data",pageUtils);
    }

    @GetMapping("/all")
    @ApiOperation("获取所有的poster信息")
    public R getAll(Integer pageNum,Integer pageSize){
        QueryWrapper<Poster> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.orderByDesc("create_time");
        Page<Poster> objectPage = new Page<>(pageNum,pageSize);
        Page<Poster> page = posterService.page(objectPage, objectQueryWrapper);
        List<Poster> records = page.getRecords();
        records.stream().filter(b->{
            b.setAvatar(userService.getById(b.getUserid()).getAvatar());
            b.setUsername(userService.getById(b.getUserid()).getNickname());
            QueryWrapper<Like> objectQueryWrapper1 = new QueryWrapper<>();
            objectQueryWrapper1.eq("posterid",b.getId());
            objectQueryWrapper1.eq("status",1);
            int count = likeService.count(objectQueryWrapper1);
            b.setLikes(count);
            return true;
        }).collect(Collectors.toList());

        PageUtils pageUtils = new PageUtils(records,page.getTotal(),page.getSize(),page.getCurrent());
        return R.success().put("data",pageUtils);
    }

    @GetMapping("/type")
    @ApiOperation("获取所有的类型为type的信息")
    public R getType(String type, Integer pageNum,Integer pageSize){
        QueryWrapper<Poster> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("type",type);
        Page<Poster> objectPage = new Page<>(pageNum,pageSize);
        Page<Poster> page = posterService.page(objectPage, objectQueryWrapper);
        List<Poster> records = page.getRecords();
        records.stream().filter(b->{
            b.setAvatar(userService.getById(b.getUserid()).getAvatar());
            b.setUsername(userService.getById(b.getUserid()).getNickname());
            QueryWrapper<Like> objectQueryWrapper1 = new QueryWrapper<>();
            objectQueryWrapper1.eq("posterid",b.getId());
            objectQueryWrapper1.eq("status",1);
            int count = likeService.count(objectQueryWrapper1);
            b.setLikes(count);
            return true;
        }).collect(Collectors.toList());
        PageUtils pageUtils = new PageUtils(records,page.getTotal(),page.getSize(),page.getCurrent());
        return R.success().put("data",pageUtils);
    }

    @GetMapping("/localRange")
    @ApiOperation("获取本地poster信息")
    public R getLocalRange( @ApiParam("range")Double range, @ApiParam("lat") Number lat,@ApiParam("lng") Number lng ,@ApiParam("type") String type, @ApiParam("pageNumber") Integer pageNum,@ApiParam("pageSize") Integer pageSize){
        QueryWrapper<Poster> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.and(
                Wrapper->Wrapper.between("latitude",lat.floatValue()-range,lat.floatValue()+range)
                        .between("longtitude",lng.floatValue()-range,lng.floatValue()+range)
                        .eq("type",type)
        );
        Page<Poster> objectPage = new Page<>(pageNum,pageSize);
        Page<Poster> page = posterService.page(objectPage, objectQueryWrapper);
        List<Poster> records = page.getRecords();
        records.stream().filter(b->{
            b.setAvatar(userService.getById(b.getUserid()).getAvatar());
            b.setUsername(userService.getById(b.getUserid()).getNickname());
            QueryWrapper<Like> objectQueryWrapper1 = new QueryWrapper<>();
            objectQueryWrapper1.eq("posterid",b.getId());
            objectQueryWrapper1.eq("status",1);
            int count = likeService.count(objectQueryWrapper1);
            b.setLikes(count);
            return true;
        }).collect(Collectors.toList());
        PageUtils pageUtils = new PageUtils(records,page.getTotal(),page.getSize(),page.getCurrent());
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
        List<Poster> records = page.getRecords();
        records.stream().filter(b->{
            b.setAvatar(userService.getById(b.getUserid()).getAvatar());
            b.setUsername(userService.getById(b.getUserid()).getNickname());
            QueryWrapper<Like> objectQueryWrapper1 = new QueryWrapper<>();
            objectQueryWrapper1.eq("posterid",b.getId());
            objectQueryWrapper1.eq("status",1);
            int count = likeService.count(objectQueryWrapper1);
            b.setLikes(count);
            return true;
        }).collect(Collectors.toList());
        PageUtils pageUtils = new PageUtils(records,page.getTotal(),page.getSize(),page.getCurrent());
        return R.success().put("data",pageUtils);
    }

    @GetMapping("/info")
    @ApiOperation("获取单个poster信息")
//    @RolesAllowed({Constant.LOGIN})
    public R getInfo(Long posterId){
        Poster byId = posterService.getById(posterId);
        if(byId==null){
            return R.error("您找的动态不存在！");
        }
        byId.setUsername(userService.getById(byId.getUserid()).getNickname());
        byId.setAvatar(userService.getById(byId.getUserid()).getAvatar());
        QueryWrapper<Like> objectQueryWrapper1 = new QueryWrapper<>();
        objectQueryWrapper1.eq("posterid",posterId);
        objectQueryWrapper1.eq("status",1);
        int count = likeService.count(objectQueryWrapper1);
//        log.info("count {}",count);
        byId.setLikes(count);
        return R.success().put("data",byId);
    }
    @DeleteMapping("/del")
    @ApiOperation("删除单个poster信息")
//    @RolesAllowed({Constant.LOGIN})
    public R del(Long posterId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Poster byId = posterService.getById(posterId);
        if(byId==null){
            return R.error("你要删除的动态不存在");
        }
        UserEntity loginUser = authUtil.getLoginUser(authentication);
        if(loginUser.getId()!=byId.getUserid()){
            return R.error("不能删除别人的动态");
        }

        posterService.removeById(posterId);
        return R.success("删除成功");
    }
}
