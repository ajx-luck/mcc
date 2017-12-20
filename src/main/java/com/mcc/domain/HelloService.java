package com.mcc.domain;

/**
 * Created by B04e on 2017/12/7.
 */
public class HelloService {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String sayHello(){
        return "Hello" + msg;
    }
}
