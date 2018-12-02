package com.andey.config.utils;

import com.alibaba.druid.util.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by jiangbin on 2018/12/2.
 */
public class Md5PasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return DigestUtils.md5Hex((String)rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return StringUtils.equalsIgnoreCase(DigestUtils.md5Hex((String)rawPassword), encodedPassword);
    }
}
