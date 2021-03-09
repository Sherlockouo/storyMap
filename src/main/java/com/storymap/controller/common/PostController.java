package com.storymap.controller.common;

import com.storymap.entity.Poster;
import com.storymap.service.PosterService;
import com.storymap.util.common.Constant;
import com.storymap.util.common.HttpUtl;
import com.storymap.util.common.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/poster")
public class PostController {
    @Autowired
    PosterService posterService;

    @Autowired
    HttpUtl httpUtl;

    @PostMapping("/post")
    @ApiOperation("发poster")
    @RolesAllowed({Constant.LOGIN})
    public R post(Poster poster, List<String> files){
        /**
         * 转换 地址2经纬度 将files 存入files
         */
        String address = poster.getAddress();

        Map<String, Number> ll = httpUtl.getAddress(address);
        /*
        地址解析
         */
        poster.setLatitude(ll.get(Constant.LATITUDE).floatValue());
        poster.setLongitude(ll.get(Constant.LONGTITUDE).floatValue());
        StringBuilder sb = new StringBuilder();
        for(String x:files){
            sb.append(x+"#");
        }
        poster.setFiles(sb.toString());

        posterService.save(poster);

        return R.success("发布成功");
    }

    @GetMapping("/self")
    @ApiOperation("获取自己的poster信息")
    public R getself(){

        return R.success();
    }

}
