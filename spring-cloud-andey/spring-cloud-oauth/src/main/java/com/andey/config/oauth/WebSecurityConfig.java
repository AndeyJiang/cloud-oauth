package com.andey.config.oauth;

import com.andey.service.UsernameUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by jiangbin on 2018/11/26.
 */
@Configuration
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter{

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //先把basic校验关掉试试效果
        http.httpBasic().disable();
        // 配置登陆页/login并允许访问
//        http.formLogin().loginPage("/login").permitAll()
//        .and().authorizeRequests().anyRequest().authenticated()
        //a不需要csrf，即允许跨域
        http.csrf().disable();
    }

}
