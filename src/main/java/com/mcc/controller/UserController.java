package com.mcc.controller;

import com.mcc.comm.Const;
import com.mcc.domain.User;
import com.mcc.service.UserService;
import com.mcc.utils.AwardUtils;
import com.mcc.utils.CookieUtils;
import com.mcc.utils.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public String userCenter(ModelMap map, HttpServletRequest request) {
        User user = mUserService.findUserByCookie(request);
        map.addAttribute("user", user);
        map.addAttribute("coin", FormatUtils.showCoin(user.getCoin()));
        map.addAttribute("refcoin", FormatUtils.showCoin(AwardUtils.getRecommendAward(user.getReferrerCoin(), 0)));
        return "index";

    }

    @RequestMapping("/news")
    public String news(ModelMap map, HttpServletRequest request) {
        User user = mUserService.findUserByCookie(request);
        map.addAttribute("user", user);
        return "news";

    }

    @RequestMapping("/map")
    public String map(ModelMap map, HttpServletRequest request) {
        User user = mUserService.findUserByCookie(request);
        map.addAttribute("user", user);
        return "map";

    }

    @RequestMapping("/download")
    public String download(ModelMap map, HttpServletRequest request) {
        User user = mUserService.findUserByCookie(request);
        map.addAttribute("user", user);
        return "download";

    }

    @RequestMapping("/member")
    public String member(ModelMap map, HttpServletRequest request) {
        User user = mUserService.findUserByCookie(request);
        List<User> contacts = mUserService.getAllContacts(user);
        map.addAttribute("user", user);
        map.addAttribute("contacts",contacts);
        return "member";

    }

}
