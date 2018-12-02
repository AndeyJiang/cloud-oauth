package com.andey.config.oauth;

import com.andey.config.utils.Md5PasswordEncoder;
import com.andey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by jiangbin on 2018/11/26.
 */
@Configuration
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter{

    //自动注入userDetailService
    @Autowired
    private UserService userDetailsService;

    //自动注入加密方式
    @Bean
    public PasswordEncoder myEncoder(){
        return new Md5PasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // 设置userDetailsService
        provider.setUserDetailsService(userDetailsService);
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
//        http.requestMatchers()
//            .antMatchers("/oauth/**","/login/**")
//            .and()
//            .authorizeRequests()
//            .antMatchers("/oauth/**").authenticated();

         http.formLogin().loginPage("/login").permitAll()
        .and().authorizeRequests().anyRequest().authenticated()
        //a不需要csrf，即允许跨域
        .and().csrf().disable();
    }

}
