package com.andey.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by jiangbin and mofei on 2018/11/27.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter
{

    /**
     * ftl视图
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/login").setViewName("login");
        //registry.addViewController("/phoneLogin").setViewName("login");
        //registry.addViewController("/").setViewName("authorize");
    }

    /**
     * 静态资源
     * @param  registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

    /**
     * 省去了配置文件的繁多配置
     */
}
