package com.mcc;

import com.mcc.domain.User;
import com.mcc.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by B04e on 2018/1/25.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserServiceImpl mUserService;

    @Test
    public void createUserTest(){
        User user = new User();
        user.setUserName("hmm004");
        user.setPassWord("ax123456");
        user.setCoin(100000000L);
        user.setGrade(1);
        user.setEmail("54678255@qq.com");
        long time = new Date().getTime();
        user.setCreateTime(time);
        user.setLastModifyTime(time);
        user.setProfilePicture("default");
        user.setMachineId(1L);
        user.setPhone("188");
        user.setTopUserName("hmm002");
        user.setReferrerCoin(0L);
        user.setTonken("1");
        mUserService.createUser(user);
    }

    @Test
    public void loginTest(){
        String userName = "hmm001";
        String password = "ax123456";
        assert (null != mUserService.findUserByUserNameAndPsd(userName,password));
    }

    @Test
    public void testAddContactAccount(){
        User user = mUserService.findUserByUserName("hmm001");
        mUserService.addContactAccount(user,"hmm002","ax123456","aa123456");
        mUserService.addContactAccount(user,"hmm003","ax123456","aa123456");
        mUserService.addContactAccount(user,"hmm004","ax123456","aa123456");
        mUserService.addContactAccount(user,"hmm005","ax123456","aa123456");
        mUserService.addContactAccount(user,"hmm006","ax123456","aa123456");
    }

    @Test
    public void testGetAllContacts(){
        User user = mUserService.findUserByUserName("hmm001");
        List<User> users = mUserService.getAllContacts(user);
        assert (users!=null);
    }

    @Test
    public void testChangeUserByContactId(){
        User user = mUserService.findUserByUserName("hmm001");
        User changeUser = mUserService.changeUserByContactId(user,2L);
        assert (changeUser != null);
    }

    @Test
    public void testGetTopUser(){
        List<User> lists = mUserService.getTopUser("hmm001");
        assert (lists != null);
    }
}
