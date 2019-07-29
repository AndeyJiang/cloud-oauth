package com.andey.config.smscode;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * added by Andy Jiang  27th Jul 2019
 *
 * Mobile captcha login interceptor
 */
public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

     public  static  final String ANDY_SECURITY_FORM_PHONE_KEY="phone";
     public  static  final String ANDY_SECURITY_FORM_VERIFYCODE_KEY="verifyCode";

     private  String phoneParameter=ANDY_SECURITY_FORM_PHONE_KEY;
     private  String verifyCodeParameter=ANDY_SECURITY_FORM_VERIFYCODE_KEY;

     private boolean postOnly=true;
    /**
     * URL to intercept
     */
    protected SmsCodeAuthenticationFilter() {
        super(new AntPathRequestMatcher("/authentication/phoneLogin"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        if(postOnly && !request.getMethod().equals("POST")){

            throw  new AuthenticationServiceException(
                    "Authentication method not supported"+request.getMethod());
        }

        String phone=obtainMobilePhone(request);
        String verifyCode=obtainVerifyCode(request);
        if(phone == null){
            phone = "";

        }
        if(verifyCode == null){
            verifyCode = "";
        }
        phone.trim();

        /**
         * specify token type to be generated
         */
        SmsCodeAuthenticationToken authRequest=new SmsCodeAuthenticationToken(phone);

        setDetails(request,authRequest);
        /**
         * specified by AuthenticationManager which Provider to handler
         */
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    protected  String obtainMobilePhone(HttpServletRequest request){
        String phone=request.getParameter(phoneParameter);
        return phone;
    }

    protected  String obtainVerifyCode(HttpServletRequest request){
        String verifyCode=request.getParameter(verifyCodeParameter);
        return verifyCode;
    }

    protected void  setDetails(HttpServletRequest httpRequest,SmsCodeAuthenticationToken authRequest){
        authRequest.setDetails(authenticationDetailsSource.buildDetails(httpRequest));

    }
}
