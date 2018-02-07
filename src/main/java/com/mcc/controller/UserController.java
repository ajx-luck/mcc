package com.mcc.controller;

import com.mcc.comm.Const;
import com.mcc.domain.User;
import com.mcc.service.UserService;
import com.mcc.utils.CookieUtils;
import com.mcc.utils.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by B04e on 2018/2/7.
 */
@Controller
public class UserController {
    @Autowired
    UserService mUserService;

    @Autowired
    private StringRedisTemplate mRedisTemplate;

    @RequestMapping("/usercenter")
    public String userCenter(ModelMap map, HttpServletRequest request){
        Cookie cookie = CookieUtils.get(request, Const.TOKEN);
        String tokenValue = mRedisTemplate.opsForValue().get(String.format(Const.TOKEN_PREFIX,cookie.getValue()));
        User user = mUserService.findUserByUserName(tokenValue);
        if(user != null){
            map.addAttribute("user",user);
            map.addAttribute("coin", FormatUtils.showCoin(user.getCoin()));
            return "index";
        }else{
            return "redirect:/login";
        }
    }

}
