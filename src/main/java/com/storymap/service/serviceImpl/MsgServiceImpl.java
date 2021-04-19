package com.storymap.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.storymap.dao.MsgDao;
import com.storymap.entity.Message;
import com.storymap.service.MsgService;
import org.springframework.stereotype.Service;

@Service("msgService")
public class MsgServiceImpl extends ServiceImpl<MsgDao, Message> implements MsgService {
}
