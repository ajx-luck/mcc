package com.mcc.controller;

import com.mcc.comm.Const;
import com.mcc.service.TradeService;
import com.mcc.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by B04e on 2018/2/1.
 */
@RestController
public class TradeController {
    @Autowired
    TradeService mTradeService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping(value = "/tradinguser",method = RequestMethod.GET)
    public String tradeByUserName(ModelMap map, HttpServletRequest request){
        Cookie cookie = CookieUtils.get(request, Const.TOKEN);
        String tokenValue = redisTemplate.opsForValue().get(String.format(Const.TOKEN_PREFIX,cookie.getValue()));
        return mTradeService.findAllByUserName(tokenValue,1);
    }
}
