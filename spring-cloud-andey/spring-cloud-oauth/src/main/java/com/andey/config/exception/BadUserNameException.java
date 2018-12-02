package com.andey.config.exception;

import lombok.Data;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by jiangbin on 2018/12/2.
 */
@Data
public class BadUserNameException extends UsernameNotFoundException {
    private Integer code;
    public BadUserNameException(Integer code,String msg) {

        super(msg);
        this.code=code;
    }
}
