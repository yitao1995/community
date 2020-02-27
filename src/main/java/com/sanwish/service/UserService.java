package com.sanwish.service;

import com.sanwish.mapper.UserMapper;
import com.sanwish.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Sanwish on 2020/2/28.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public void createOrUpdate(User user) {

        User dbUser = userMapper.findByAccountId(user.getAccountId());
        if(dbUser==null){
            //插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else{
            //更新
            dbUser.setGmtModified(user.getGmtCreate());
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            userMapper.update(dbUser);
        }

    }
}
