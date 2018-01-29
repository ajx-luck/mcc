package com.mcc;

import com.mcc.domain.Machine;
import com.mcc.repository.MachineRepository;
import com.mcc.service.CoinService;
import com.mcc.service.impl.CoinServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by B04e on 2018/1/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CoinServiceTest {
    @Autowired
    CoinService mCoinService;
    @Autowired
    MachineRepository mMachineRepository;

    @Test
    public void testTrade(){
        String user1 = "hmm001";
        String user2 = "hmm002";
        try {
            mCoinService.trade(user1,user2,1000000000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addCoinTest(){
        mCoinService.addCoin();
    }

    @Test
    public void addMachineTest(){
        Machine machine = new Machine();
        machine.setCreateTime(new Date().getTime());
        machine.setMachineName("1号机");
        machine.setOutCoin(821917L);
        machine.setPrice(100000000L);
        machine.setMachineDesc("300个");
        mMachineRepository.save(machine);
    }
}
