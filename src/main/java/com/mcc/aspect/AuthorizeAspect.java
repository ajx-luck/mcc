package com.mcc.aspect;

import com.mcc.comm.Const;
import com.mcc.exception.AuthorizeException;
import com.mcc.utils.CookieUtils;
import groovy.util.logging.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by B04e on 2018/2/1.
 */
@Aspect
@Component
@Slf4j
public class AuthorizeAspect {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * com.mcc.controller.TradeController.*(..))" +
            "&& !execution(public * com.mcc.controller.LoginController.*(..))")
    public void verify(){}

    @Before("verify()")
    public void doVerify(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //查询cookie
        Cookie cookie = CookieUtils.get(request, Const.TOKEN);
        if(cookie == null){
            System.out.println("【登陆校验】Cookie中查不到token");
            throw new AuthorizeException();
        }

        //从Redis中查询
        String tokenValue = redisTemplate.opsForValue().get(String.format(Const.TOKEN_PREFIX,cookie.getValue()));
        if(StringUtils.isEmpty(tokenValue)){
            System.out.println("【登陆校验】Redis中查不到token");
            throw new AuthorizeException();
        }

    }
}
