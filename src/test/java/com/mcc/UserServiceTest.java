package com.mcc;

import com.mcc.domain.User;
import com.mcc.service.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

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
        user.setUserName("hmm002");
        user.setPassWord("ax123456");
        user.setCoin(1000000000L);
        user.setGrade(1);
        user.setEmail("54678255@qq.com");
        long time = new Date().getTime();
        user.setCreateTime(time);
        user.setLastModifyTime(time);
        user.setProfilePicture("default");
        user.setMachineId(1L);
        user.setPhone("188");
        user.setTopUserName("hmm001");
        user.setReferrerCoin(0L);
        mUserService.createUser(user);
    }

    @Test
    public void loginTest(){
        String userName = "hmm001";
        String password = "ax123456";
        assert (null != mUserService.findUserByUserNameAndPsd(userName,password));
    }
}
