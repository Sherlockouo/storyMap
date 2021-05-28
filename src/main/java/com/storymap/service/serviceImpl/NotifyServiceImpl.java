package com.storymap.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.storymap.dao.MsgDao;
import com.storymap.dao.NotifyDao;
import com.storymap.entity.Message;
import com.storymap.entity.Notify;
import com.storymap.service.MsgService;
import com.storymap.service.NotifyService;
import org.springframework.stereotype.Service;

@Service("notifyService")
public class NotifyServiceImpl extends ServiceImpl<NotifyDao, Notify> implements NotifyService {
}
