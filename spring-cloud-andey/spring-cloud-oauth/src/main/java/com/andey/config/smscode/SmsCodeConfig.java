package com.andey.config.smscode;

import com.andey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author added by Andy JIang
 *
 * @Date  29th Jul 2019
 */
@Configuration
public class SmsCodeConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>  {
//    @Autowired
//    private AuthenticationSuccessHandler authencationSuccessHandler;
//
//    @Autowired
//    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private UserService userService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        /**
         * filter configuration
         */
        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter=new SmsCodeAuthenticationFilter();
        smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        //smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(authencationSuccessHandler);
       // smsCodeAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        /**
         * provider configuration
         */
        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider=new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserService(userService);
        http.authenticationProvider(smsCodeAuthenticationProvider).addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
