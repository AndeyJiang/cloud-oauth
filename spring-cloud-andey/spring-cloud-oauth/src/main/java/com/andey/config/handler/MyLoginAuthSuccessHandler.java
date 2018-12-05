package com.andey.config.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by fp295 on 2018/6/16.
 * 登陆成功处理，移动端登陆成功后还需做绑定操作
 */
//public class MyLoginAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        System.out.println("onAuthenticationSuccess");
//
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        System.out.print(userDetails.getUsername());
//        super.onAuthenticationSuccess(request, response, authentication);
//    }
//}