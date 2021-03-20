package com.storymap.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.storymap.dao.LikeDao;
import com.storymap.dao.PosterDao;
import com.storymap.entity.Like;
import com.storymap.entity.Poster;
import com.storymap.service.LikeService;
import com.storymap.service.PosterService;
import org.springframework.stereotype.Service;

@Service("likeService")
public class LikeServiceImpl extends ServiceImpl<LikeDao, Like> implements LikeService {

}
