package com.mcc.service.impl;

import com.mcc.domain.User;
import com.mcc.repository.UserRepository;
import com.mcc.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by B04e on 2018/1/24.
 */
@Service
public class UserServiceImpl {
    @Autowired
    UserRepository mUserRepository;

    /**
     * 创建用户
     * @param user
     */
    public void createUser(User user){
        user.setAliveDay(365);
        user.setWalletAddress(CommonUtils.getMD5(user.getUserName()+user.getPhone()+user.getEmail()));
        mUserRepository.save(user);
    }

    /**
     * 用户登录
     * @param userName
     * @param password
     * @return
     */
    public User findUserByUserNameAndPsd(String userName,String password){
        if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)){
            return null;
        }else{
            return mUserRepository.findUserByUserNameAndPassWord(userName, password);
        }
    }

}
