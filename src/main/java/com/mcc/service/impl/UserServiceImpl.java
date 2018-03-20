package com.mcc.service.impl;

import com.mcc.comm.Const;
import com.mcc.domain.User;
import com.mcc.exception.CreateUserException;
import com.mcc.repository.MachineRepository;
import com.mcc.repository.UserRepository;
import com.mcc.service.UserService;
import com.mcc.utils.AwardUtils;
import com.mcc.utils.CommonUtils;
import com.mcc.utils.StringUtils;
import com.mcc.utils.SymmetricEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by B04e on 2018/1/24.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository mUserRepository;
    @Autowired
    MachineRepository mMachineRepository;

    /**
     * 创建用户
     *
     * @param user
     */
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    @Override
    public void createUser(User user) {
        String topUserName = user.getTopUserName();
        User user1 = mUserRepository.findUserByUserName(topUserName);
        long price = mMachineRepository.findOne(user.getMachineId()).getPrice();
        if (user1.getCoin() >= price) {
            user.setAliveDay(365);
            user.setWalletAddress(CommonUtils.getMD5(user.getUserName() + user.getPhone() + user.getEmail()));
            user.setWalletKey(SymmetricEncoder.AESEncode(Const.ENCODER_KEY, user.getWalletAddress()));
            mUserRepository.save(user);
            user1.setCoin(user1.getCoin() - price);
            user1.setCoin(user1.getCoin() + AwardUtils.getRecommendAward(price, user1.getReferrerCoin()));
            user1.setReferrerCoin(user1.getReferrerCoin() + price);
            user.setTonken("1");
            mUserRepository.save(user1);
        } else {
            throw new CreateUserException();
        }


    }

    /**
     * 用户登录
     *
     * @param userName
     * @param password
     * @return
     */
    @Override
    public User findUserByUserNameAndPsd(String userName, String password) {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            return null;
        } else {
            return mUserRepository.findUserByUserNameAndPassWord(userName, password);
        }
    }

    /**
     * 根据钱包查询User
     *
     * @param walletAddress
     * @return
     */
    @Override
    public User findUserByWalletAddress(String walletAddress) {
        if (StringUtils.isEmpty(walletAddress) || StringUtils.isEmpty(walletAddress)) {
            return null;
        } else {
            return mUserRepository.findUserByWalletAddress(walletAddress);
        }
    }

    @Override
    public User findUserByUserName(String userName) {
        if (StringUtils.isEmpty(userName)) {
            return null;
        } else {
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

    @Override
    public User addContactAccount(User user, String userName, String psd, String payPsd) {
        User contactUser = mUserRepository.findUserByUserName(userName);
        if(contactUser != null && contactUser.getPassWord().equals(psd) && contactUser.getPayWord().equals(payPsd)){
            contactUser.setContactIds(StringUtils.addContactIds(contactUser.getContactIds(),user.getId()+""));
            user.setContactIds(StringUtils.addContactIds(user.getContactIds(),contactUser.getId()+""));
            mUserRepository.save(user);
            mUserRepository.save(contactUser);
            return user;
        }else{
            return null;
        }
    }

    @Override
    public User changeUserByContactId(User user,Long id) {
        if(user.getContactIds().contains(id+"")){
            return mUserRepository.findUserById(id);
        }
        return null;
    }

    @Override
    public List<User> getAllContacts(User user) {
        List<Long> ids = Arrays.asList(user.getContactIds().split(",")).stream().map(s -> Long.parseLong(s.trim()))
                .collect(Collectors.toList());
        return mUserRepository.findUsersByIdIn(ids);
    }


}
