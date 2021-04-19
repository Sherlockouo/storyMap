package com.storymap.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.storymap.entity.Message;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface  MsgDao extends BaseMapper<Message> {
}
