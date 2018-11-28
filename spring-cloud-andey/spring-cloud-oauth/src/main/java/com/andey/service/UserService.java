package com.andey.service;

import com.andey.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Created by jiangbin on 2018/11/27.
 */
public interface UserService extends UserDetailsService, SocialUserDetailsService {

    User findByUserName(String userName);
}
