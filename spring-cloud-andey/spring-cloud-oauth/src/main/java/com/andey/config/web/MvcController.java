package com.andey.config.web;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by jiangbin on 2018/11/27.
 */
@Controller
@SessionAttributes({"authorizationRequest"})
public class MvcController {

    /**
     * 授权页面
     * @param  model
     */
    @RequestMapping("/oauth/confirm_access")
    public ModelAndView authorizePage(Map<String, Object> model) {
        // 获取用户名
        String userName = ((UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal())
                .getUsername();
        model.put("userName", userName);
        return new ModelAndView("authorize", model);
    }
}
