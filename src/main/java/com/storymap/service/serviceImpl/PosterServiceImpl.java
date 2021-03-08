package com.storymap.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.storymap.dao.PosterDao;
import com.storymap.entity.Poster;
import com.storymap.service.PosterService;
import org.springframework.stereotype.Service;

@Service("posterService")
public class PosterServiceImpl extends ServiceImpl<PosterDao, Poster> implements PosterService {

}
