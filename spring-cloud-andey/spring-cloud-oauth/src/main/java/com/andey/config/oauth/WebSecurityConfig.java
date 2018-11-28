package com.andey.config.oauth;

import com.andey.service.UserService;
import com.andey.service.UsernameUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by jiangbin on 2018/11/26.
 */
@Configuration
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter{

    //自动注入userDetailService
    @Autowired
    private UserService userService;

    //自动注入加密方式
    @Bean
    public BCryptPasswordEncoder myEncoder(){
        return new BCryptPasswordEncoder(6);
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // 设置userDetailsService
        provider.setUserDetailsService(userService);
        // 禁止隐藏用户未找到异常
        provider.setHideUserNotFoundExceptions(false);
        // 使用BCrypt进行密码的hash
        provider.setPasswordEncoder(myEncoder());
        return provider;
    }

    //用户及授权信息的管理集合
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
         //先把basic校验关掉试试效果
         http.httpBasic().disable();
         // 配置登陆页/login并允许访问
         http.formLogin().loginPage("/login").permitAll()
        .and().authorizeRequests().anyRequest().authenticated()
        //a不需要csrf，即允许跨域
        .and().csrf().disable();
    }

}
