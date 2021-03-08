package com.storymap.controller.common;

import com.storymap.entity.Poster;
import com.storymap.service.PosterService;
import com.storymap.util.common.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/poster")
public class PostController {
    @Autowired
    PosterService posterService;

    @PostMapping("/post")
    @ApiOperation("发poster")
    public R post(Poster poster){

        return R.success();
    }
}
