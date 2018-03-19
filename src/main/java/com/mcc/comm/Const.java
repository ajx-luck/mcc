package com.mcc.comm;

/**
 * Created by B04e on 2017/11/22.
 */
public interface Const {
    //交易类型
    static final int TRADE = 1;
    static final int SYSTEM_ADD = 2;
    static final int COMMEND_ADD = 3;
    //AES加密key
    static final String ENCODER_KEY = "mcc%/123.opx";
    //Redis_key
    String TOKEN_PREFIX = "token_%s";

    Integer EXPIRE = 60*60*24*30; //2小时
    //cookie 登陆信息
    String TOKEN = "token";

    //用户名
    String USERNAME = "username";

    //等级
    String GRADE = "grade";
}
