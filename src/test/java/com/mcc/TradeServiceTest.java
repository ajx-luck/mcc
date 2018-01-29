package com.mcc;

import com.mcc.domain.User;
import com.mcc.service.TradeService;
import com.mcc.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by B04e on 2018/1/25.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeServiceTest {
    @Autowired
    TradeService mTradeService;

    @Test
    public void testFindAllByUserName(){
        String userName = "hmm001";
        String str = mTradeService.findAllByUserName(userName,0);
        System.out.println(str);
    }
}
