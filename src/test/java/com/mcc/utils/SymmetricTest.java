package com.mcc.utils;

import com.mcc.comm.Const;
import com.mcc.domain.User;
import com.mcc.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by B04e on 2018/2/1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SymmetricTest {

    @Autowired
    UserService mUserService;

    @Test
    public void testEncoder(){
        User user = mUserService.findUserByUserName("hmm001");
        String aes = SymmetricEncoder.AESEncode(Const.ENCODER_KEY,user.getWalletAddress());
        String walletAddr = SymmetricEncoder.AESDncode(Const.ENCODER_KEY,"Akkz4eI8MYxu+hEnMn3SB0A4wJgoG8vdlj7m34ini9qy3kvDhXY2mkzFYRBrifJv");
        assert walletAddr.equals(user.getWalletAddress());
    }
}
