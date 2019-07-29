package com.andey.config.oauth;
import com.andey.config.smscode.SmsCodeAuthenticationProvider;
import com.andey.config.smscode.SmsCodeConfig;
import com.andey.config.utils.Md5PasswordEncoder;
import com.andey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

/**
 * Created by jiangbin on 2018/11/26.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter{

    @Autowired
    SmsCodeAuthenticationProvider smsCodeAuthenticationProvider;

    //自动注入userDetailService
    @Autowired
    private UserService userDetailsService;

    //自动注入加密方式
    @Bean
    public PasswordEncoder myEncoder(){
        return new Md5PasswordEncoder();
    }

    @Autowired
    private  SmsCodeConfig smsCodeConfig;


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // customize userDetailsService
        provider.setUserDetailsService(userDetailsService);
        // do not hidden exception by user
        provider.setHideUserNotFoundExceptions(false);
        // use Md5 for passwords
        provider.setPasswordEncoder(myEncoder());
        return provider;
    }

    //user and authentication information management collection
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
       return super.authenticationManagerBean();
    }

    //WebSecurityConfigurerAdapter
    /**Note: Andy Jiang  Jul 25th 2019
     *
     if you add a path into antMatchers()
     it means you need to login before you can access it.
     Don't confused with the ResourceServer definition,
     that means authorization but not login certification
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
         //close the basic security login form
         http.httpBasic().disable();
         http.apply(smsCodeConfig);
         http
                  .requestMatchers().antMatchers("/login/**","/oauth/**","/static/**","/authentication/**")
                  .and()
                  .authorizeRequests()
                 .antMatchers("/oauth/**","/authentication/**")
                  .authenticated()
                  .and()
                  .formLogin()
                  //point out login page
                  .loginPage("/login")
                  .permitAll()

        //not need csrf，cross-domain is allowed
        .and().csrf().disable();


    }

    protected UserDetailsService userDetailsService() {

        return userDetailsService;

    }

}
