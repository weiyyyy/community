package com.weiyang.community.service;

import com.weiyang.community.mapper.UserMapper;
import com.weiyang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        User dbuser =userMapper.findByAccountId(user.getAccountId());
        if (dbuser==null){
            //插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else {
            //更新
            dbuser.setGmtCreate(System.currentTimeMillis());
            dbuser.setAvatarUrl(user.getAvatarUrl());
            //dbuser.setName(user.getName());
            dbuser.setToken(user.getToken());
            userMapper.updata(dbuser);
        }
    }
}
