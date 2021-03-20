package com.storymap.controller.common;

import com.storymap.service.LikeService;
import com.storymap.util.common.Constant;
import com.storymap.util.common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/follow")
public class FollowController {
    @Autowired
    LikeService likeService;

    @PostMapping("/dofollow")
    @RolesAllowed({Constant.LOGIN})
    public R like(){
        return R.success();
    }

    @PutMapping("/unfollow")
    @RolesAllowed({Constant.LOGIN})
    public R unlike(){
        return R.success();
    }

    @GetMapping("/list")
    @RolesAllowed({Constant.LOGIN})
    public R likelist(){
        return R.success();
    }
}
