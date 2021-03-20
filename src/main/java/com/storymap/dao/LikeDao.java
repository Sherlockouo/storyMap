package com.storymap.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.storymap.entity.Like;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikeDao extends BaseMapper<Like> {
}
