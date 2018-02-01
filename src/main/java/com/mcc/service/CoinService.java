package com.mcc.service;

/**
 * Created by B04e on 2018/1/26.
 */
public interface CoinService {
    void addCoin();

    void tradeByUserName(String fromUserName,String toUserName,final long coin) throws Exception ;

    void tradeByWalletAddress(String fromUserWallet,String toUserWallet,final long coin) throws Exception ;
}
