package com.storymap.controller.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.storymap.entity.Notify;
import com.storymap.service.NotifyService;
import com.storymap.util.common.AuthUtil;
import com.storymap.util.common.Constant;
import com.storymap.util.common.PageUtils;
import com.storymap.util.common.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/notify")
@ApiOperation("通知")
public class NotifyController {
    @Autowired
    NotifyService notifyService;

    @Autowired
    AuthUtil authUtil;

    @PostMapping("/post")
    @ApiOperation("发通知")
    @RolesAllowed({Constant.ADMIN})
    public R post(Notify notify){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        notifyService.save(notify);
        return R.success();
    }

    @GetMapping("/all")
    @ApiOperation("获取所有通知")
    @RolesAllowed({Constant.ADMIN})
    public R getall(Integer pageSize, Integer pageNum){
        QueryWrapper<Notify> objectQueryWrapper = new QueryWrapper<>();
        Page<Notify> page = new Page<>(pageNum, pageSize);
        Page<Notify> page1 = notifyService.page(page, objectQueryWrapper);

        return R.success().put("data",new PageUtils(page1));
    }

    @GetMapping("/last")
    @ApiOperation("获取最后一条通知 微信端用")
    public R getlast(){
        QueryWrapper<Notify> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.orderByDesc("create_date");
        List<Notify> list = notifyService.list(objectQueryWrapper);
        return R.success().put("data",list.get(0));
    }
}
