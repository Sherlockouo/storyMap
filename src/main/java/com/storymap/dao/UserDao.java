package com.storymap.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.storymap.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
}
