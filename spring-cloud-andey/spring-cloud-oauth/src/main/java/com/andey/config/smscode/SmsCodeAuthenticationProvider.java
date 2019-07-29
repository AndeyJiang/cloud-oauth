package com.andey.config.smscode;

import com.andey.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

          SmsCodeAuthenticationToken autRequest= (SmsCodeAuthenticationToken) authentication;
        UserDetails user=userService.loadUserByUsername((String) autRequest.getPrincipal());
        if(user==null){
            throw new InternalAuthenticationServiceException("user information no found");
        }


        SmsCodeAuthenticationToken authAuthenticationResult=new SmsCodeAuthenticationToken(user,user.getAuthorities());

        authAuthenticationResult.setDetails(autRequest.getDetails());

        return authAuthenticationResult;
    }

    /**
     Across the type of token the corresponding Provider is specified by AuthenticationManager
     SmsCodeToken.class but not the Provider.class....Fuck....
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }


    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    public UserService getUserService() {
        return userService;
    }
}
