package com.storymap.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.storymap.entity.Poster;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PosterDao extends BaseMapper<Poster> {
}
