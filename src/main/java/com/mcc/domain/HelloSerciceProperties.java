package com.mcc.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by B04e on 2017/12/7.
 */
@ConfigurationProperties(prefix = "hello")
public class HelloSerciceProperties {
    private final static String MSG = "world";

    private String msg = MSG;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
