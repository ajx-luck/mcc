package com.mcc.controller;

import com.mcc.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by B04e on 2017/11/13.
 */
@Controller
public class LoginController {
    User user = new User();
    @RequestMapping("/login")
    public String login2(ModelMap map) {
        map.addAttribute("user", user);
        return "login2";
    }

    @RequestMapping("/register")
    public String register(ModelMap map){
        return "register";
    }

    @RequestMapping("/getpw")
    public String getPassword(ModelMap map){
        return "getpw";
    }

    @RequestMapping(value = "/loginIn",method = RequestMethod.POST)
    public String loginIn(User user,ModelMap map){
        if("admin".equals(user.getUserName()) && "123456".equals(user.getPassWord())){
            return "index";
        }
        map.addAttribute("user",user);
        map.addAttribute("msgError","账号密码不正确，请重试");
        return "login2";
    }

    @RequestMapping("/te")
    public String test(ModelMap map){
        return "test";
    }

    @RequestMapping("/news")
    public String news(ModelMap map){
        return "news";
    }

    @RequestMapping("/home")
    public String layout(ModelMap map){
        map.addAttribute("name","home");
        return "layout";
    }

    @RequestMapping("/map")
    public String map(ModelMap map){
        return "map";
    }

    @RequestMapping(value="/hello")
    public String hello(ModelMap map){
        return "hello";
    }

    @RequestMapping("/trading")
    public String trading(ModelMap map){
        return "trading";
    }

    @RequestMapping("/test-api")
    public String testapi(ModelMap map){
        return "test-api";
    }
}
