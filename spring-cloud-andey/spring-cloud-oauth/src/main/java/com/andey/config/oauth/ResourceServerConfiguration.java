package com.andey.config.oauth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration  extends ResourceServerConfigurerAdapter {

  private static final String RESCOURE_ID="OAUTH2V1";

  @Override
    public  void configure(ResourceServerSecurityConfigurer resources){
      resources.resourceId(RESCOURE_ID).stateless(false);
  }

    /**
      added by Andy Jiang on July  24th  2019

      Note:
      If you want to visit resources,token is necessary,it is used to authorize the authorization
      of the visitor but not the visitor's login certification.
     * @param http
     * @throws Exception
     */
  @Override
    public void configure(HttpSecurity http) throws Exception {

      http.
              //release part
              authorizeRequests()
              .antMatchers("/test/**")
              .permitAll()
              .and()
              //validate part
              .authorizeRequests()
              .antMatchers("/1234/**")
              .authenticated()

              .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
  }
}
