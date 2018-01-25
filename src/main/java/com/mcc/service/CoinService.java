package com.mcc.service;

import com.mcc.domain.User;
import com.mcc.repository.TradeRepository;
import com.mcc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;

/**
 * Created by B04e on 2018/1/16.
 */
@Service
public class CoinService {

    @Autowired
    UserRepository mUserRepository;
    @Autowired
    TradeRepository mTradeRepository;
    /**
     * JpaTransactionManager事务管理 .
     */

    /**
     * 定时任务
     */
    @Scheduled(cron="0 0 0 * * ?")
    public void addCoin(){

    }

    /**
     * 转账
     * @param fromUser 转款账户
     * @param toUser 收款账户
     * @param coin 转账金额
     */
    @Transactional(value="transactionManager", rollbackFor = Exception.class)
    public void trade(String fromUser,String toUser,final long coin) throws Exception {
        User user1 = mUserRepository.findUserByUserName(fromUser);
        User user2 = mUserRepository.findUserByUserName(toUser);
        if(user1.getCoin() >= coin) {
            user1.setCoin(user1.getCoin() - coin);
            user2.setCoin(user1.getCoin() + coin);
            mUserRepository.save(user1);
            mUserRepository.save(user2);
        }else{
            throw new Exception("金额不足");
        }
    }
}
