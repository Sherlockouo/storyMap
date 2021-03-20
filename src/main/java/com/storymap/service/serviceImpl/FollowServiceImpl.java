package com.storymap.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.storymap.dao.FollowDao;
import com.storymap.dao.PosterDao;
import com.storymap.entity.Follow;
import com.storymap.entity.Poster;
import com.storymap.service.FollowService;
import com.storymap.service.PosterService;
import org.springframework.stereotype.Service;

@Service("followService")
public class FollowServiceImpl extends ServiceImpl<FollowDao, Follow> implements FollowService {

}
