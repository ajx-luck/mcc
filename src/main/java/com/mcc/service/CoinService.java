package com.mcc.service;

/**
 * Created by B04e on 2018/1/26.
 */
public interface CoinService {
    void addCoin();
    void trade(String fromUser,String toUser,final long coin) throws Exception ;
}
