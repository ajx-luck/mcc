package com.mcc.controller;

import com.mcc.comm.Const;
import com.mcc.domain.User;
import com.mcc.service.UserService;
import com.mcc.utils.CookieUtils;
import com.mcc.utils.RandomValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    public String loginIn(User user, ModelMap map, HttpServletResponse response, HttpSession session){
        String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
        if(user.getPrePassWord() == null || (!user.getPrePassWord().equals(random))){
            map.addAttribute("user",user);
            map.addAttribute("msgError","验证码不正确，请重试");
            return "login2";
        }
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
        CookieUtils.set(response,Const.USERNAME,userInfo.getUserName(),expire);
        CookieUtils.set(response,Const.GRADE,userInfo.getGrade()+"",expire);
        return  "redirect:/usercenter";

    }

    @RequestMapping("/")
    public String index(ModelMap map) {
        return  "redirect:/usercenter";
    }

    @RequestMapping("/logout")
    public ModelAndView logout(ModelMap map,HttpServletRequest request, HttpServletResponse response){
        Cookie cookie = CookieUtils.get(request, Const.TOKEN);
        //从Redis中查询
        String tokenValue = mRedisTemplate.opsForValue().get(String.format(Const.TOKEN_PREFIX,cookie.getValue()));
        mRedisTemplate.delete(String.format(Const.TOKEN_PREFIX, tokenValue));
        CookieUtils.set(response, Const.TOKEN, "", Const.EXPIRE);
        return new ModelAndView("redirect:".concat("/login"));
    }

    @RequestMapping("/te")
    public String test(ModelMap map){
        return "test";
    }



    @RequestMapping("/home")
    public String layout(ModelMap map){
        map.addAttribute("name","home");
        return "layout";
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

    @RequestMapping("/verify_code")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
            response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            RandomValidateCodeUtils.getRandcode(request, response);//输出验证码图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
