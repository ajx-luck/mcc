package com.mcc.service.impl;

import com.mcc.comm.Const;
import com.mcc.domain.User;
import com.mcc.exception.CreateUserException;
import com.mcc.repository.MachineRepository;
import com.mcc.repository.UserRepository;
import com.mcc.service.UserService;
import com.mcc.utils.CommonUtils;
import com.mcc.utils.SymmetricEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Created by B04e on 2018/1/24.
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository mUserRepository;
    @Autowired
    MachineRepository mMachineRepository;

    /**
     * 创建用户
     * @param user
     */
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    @Override
    public void createUser(User user){
        String topUserName = user.getTopUserName();
        User user1 = mUserRepository.findUserByUserName(topUserName);
        long price = mMachineRepository.findOne(user.getMachineId()).getPrice();
        if(user1.getCoin() >= price){
            user.setAliveDay(365);
            user.setWalletAddress(CommonUtils.getMD5(user.getUserName() + user.getPhone() + user.getEmail()));
            user.setWalletKey(SymmetricEncoder.AESEncode(Const.ENCODER_KEY, user.getWalletAddress()));
            mUserRepository.save(user);
            user1.setCoin(user1.getCoin() - price);
            mUserRepository.save(user1);
        }else{
            throw new CreateUserException();
        }


    }

    /**
     * 用户登录
     * @param userName
     * @param password
     * @return
     */
    @Override
    public User findUserByUserNameAndPsd(String userName,String password){
        if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)){
            return null;
        }else{
            return mUserRepository.findUserByUserNameAndPassWord(userName, password);
        }
    }

    /**
     * 根据钱包查询User
     * @param walletAddress
     * @return
     */
    @Override
    public User findUserByWalletAddress(String walletAddress) {
        if(StringUtils.isEmpty(walletAddress) || StringUtils.isEmpty(walletAddress)){
            return null;
        }else{
            return mUserRepository.findUserByWalletAddress(walletAddress);
        }
    }

    @Override
    public User findUserByUserName(String userName) {
        if(StringUtils.isEmpty(userName)){
            return null;
        }else{
            return mUserRepository.findUserByUserName(userName);
        }
    }

    @Override
    public User updatePsd(User user, String psd) {
        user.setPrePassWord(user.getPassWord());
        user.setPassWord(psd);
        mUserRepository.save(user);
        return user;
    }

}
