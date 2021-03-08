package com.storymap.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.storymap.entity.FileEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileDao extends BaseMapper<FileEntity> {
}
