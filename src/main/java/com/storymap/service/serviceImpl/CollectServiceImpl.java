package com.storymap.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.storymap.dao.CollectDao;
import com.storymap.dao.PosterDao;
import com.storymap.entity.Collect;
import com.storymap.entity.Poster;
import com.storymap.service.CollectService;
import com.storymap.service.PosterService;
import org.springframework.stereotype.Service;

@Service("collectService")
public class CollectServiceImpl extends ServiceImpl<CollectDao, Collect> implements CollectService {

}
