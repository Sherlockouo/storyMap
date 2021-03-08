package com.storymap.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.storymap.dao.FileDao;
import com.storymap.entity.FileEntity;
import com.storymap.service.FileService;
import org.springframework.stereotype.Service;

@Service("fileService")
public class FileServiceImpl extends ServiceImpl<FileDao, FileEntity> implements FileService {
}
