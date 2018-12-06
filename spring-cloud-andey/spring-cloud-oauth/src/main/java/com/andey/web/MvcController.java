package com.andey.web;

import com.alibaba.fastjson.JSONObject;
import com.andey.entity.User;
import com.andey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jiangbin on 2018/11/27.
 */
@Controller
@SessionAttributes({"authorizationRequest"})
public class MvcController {
    @Autowired
    UserService userService;

    /**
     * 授权页面
     *scope(资源范围) 规则，oauth系统会判断：
     * 1：如果请求中含有scope，则将请求中的scope放入Principal中
     * 2：如果请求中没有scope信息，授权页面显示对应Client_id的默认scope信息
     * @param model
     */
    @RequestMapping("/oauth/confirm_access")
    public ModelAndView authorizePage(Map<String, Object> model, HttpServletRequest request) {
        // 获取用户名
         String userName = ((UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal())
                .getUsername();
        model.put("userName", userName);
        Map<String, String> scopes = (Map<String, String>) (model.containsKey("scopes") ? model.get("scopes") : request.getAttribute("scopes"));
        List<String> scopeList = new ArrayList<String>();
        for (String scope : scopes.keySet()) {
            scopeList.add(scope);
        }
        model.put("scopeList", scopeList);
        return new ModelAndView("authorize", model);
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(String userName) {

        User user=userService.findByUserName(userName);
        return JSONObject.toJSONString(user);
    }
}
