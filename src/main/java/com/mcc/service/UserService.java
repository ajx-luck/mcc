package com.mcc.service;

import com.mcc.domain.User;

/**
 * Created by B04e on 2018/1/26.
 */
public interface UserService {

    void createUser(User user);

    User findUserByUserNameAndPsd(String userName,String password);

    User findUserByWalletAddress(String walletAddress);

    User findUserByUserName(String userName);

    User updatePsd(User user,String psd);
}
