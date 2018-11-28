package com.andey.dao;

import com.andey.entity.User;

/**
 * Created by jiangbin on 2018/11/27.
 */
public interface UserMapper{

    User findByName(String userName);
}
