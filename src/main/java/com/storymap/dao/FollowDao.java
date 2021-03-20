package com.storymap.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.storymap.entity.Follow;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FollowDao extends BaseMapper<Follow> {
}
