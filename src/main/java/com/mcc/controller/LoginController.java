package com.mcc.controller;

import com.mcc.comm.Const;
import com.mcc.domain.User;
import com.mcc.service.UserService;
import com.mcc.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by B04e on 2017/11/13.
 */
@Controller
public class LoginController {
    @Autowired
    UserService mUserService;

    @Autowired
    private StringRedisTemplate mRedisTemplate;

    @RequestMapping("/login")
    public String login2(ModelMap map) {
        map.addAttribute("user", new User());
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
    public String loginIn(User user, ModelMap map, HttpServletResponse response){
        User userInfo = mUserService.findUserByUserNameAndPsd(user.getUserName(),user.getPassWord());
        if(userInfo == null){
            map.addAttribute("user",user);
            map.addAttribute("msgError","账号密码不正确，请重试");
            return "login2";
        }
        //2. 设置token至redis
        String token = UUID.randomUUID().toString();
        Integer expire = Const.EXPIRE;

        mRedisTemplate.opsForValue().set(String.format(Const.TOKEN_PREFIX, token), user.getUserName(), expire, TimeUnit.SECONDS);
        //3. 设置token至cookie
        CookieUtils.set(response, Const.TOKEN, token, expire);
        return  "index";

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

    @ResponseBody
    @RequestMapping(value = "/goapplewx",method = RequestMethod.GET)
    public ResponseEntity deletewx(ModelMap map, HttpServletRequest request){
        return new ResponseEntity("%7b%22status%22%3a%22ok%22%2c%22ticket_url%22%3a%22weixin%3a%5c%2f%5c%2fdl%5c%2fbusiness%5c%2f%3fticket%3dt9f268ef06eac0070a397864ca054a577%23wechat_redirect%22%2c%22default_ticket_url%22%3a%22weixin%3a%5c%2f%5c%2fdl%5c%2fbusiness%5c%2f%3fticket%3dt9f268ef06eac0070a397864ca054a577%23wechat_redirect%22%7d", HttpStatus.OK);
    }
}
