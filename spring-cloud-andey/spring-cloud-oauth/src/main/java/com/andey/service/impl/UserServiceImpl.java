package com.andey.service.impl;

import com.andey.config.exception.BadUserNameException;
import com.andey.dao.UserMapper;
import com.andey.entity.User;
import com.andey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.stereotype.Service;

/**
 * Created by jiangbin on 2018/11/27.
 */
@Service
public class UserServiceImpl  implements UserService{


    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user=userMapper.findByName(userName);
        if(user==null){
            throw new BadUserNameException(0,"用户名错误");
        }
        return user;
    }

    @Override
    public User findByUserName(String userName) {

        return userMapper.findByName(userName);
    }

    @Override
    public SocialUserDetails loadUserByUserId(String s) throws UsernameNotFoundException {
        return null;
    }
}
