package com.mcc.service;

import com.mcc.comm.Const;
import com.mcc.domain.Machine;
import com.mcc.domain.Trade;
import com.mcc.domain.User;
import com.mcc.repository.MachineRepository;
import com.mcc.repository.TradeRepository;
import com.mcc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by B04e on 2018/1/16.
 */
@Service
public class CoinServiceImpl implements CoinService{

    @Autowired
    UserRepository mUserRepository;
    @Autowired
    TradeRepository mTradeRepository;
    @Autowired
    MachineRepository mMachineRepository;
    List<Machine> mMachines;

    /**
     * 定时任务
     */
    @Scheduled(cron="0 0 0 * * ?")
    @Override
    public void addCoin(){
        initMachine();
        int page = 0;
        Pageable pageable = new PageRequest(page,100, Sort.Direction.ASC, "id");
        Page<User> userPage = mUserRepository.findAll(pageable);
        addCoinImpl(userPage);
        int pages = userPage.getTotalPages();
        for(page=1;page<pages;page++){
            pageable = new PageRequest(page,100, Sort.Direction.ASC, "id");
            userPage = mUserRepository.findAll(pageable);
            addCoinImpl(userPage);
        }
    }

    private void initMachine() {
        mMachines = mMachineRepository.findAll();
    }


    private void addCoinImpl(Page<User> userPage){
        for(User user:userPage){
            if(user.getAliveDay() > 0){
                long coin = mMachines.get(user.getMachineId().intValue()).getOutCoin();
                user.setCoin(user.getCoin() + coin);
            }
        }
    }

    /**
     * 转账
     * @param fromUser 转款账户
     * @param toUser 收款账户
     * @param coin 转账金额
     */
    @Transactional(value="transactionManager", rollbackFor = Exception.class)
    @Override
    public void trade(String fromUser,String toUser,final long coin) throws Exception {
        long time = new Date().getTime();
        User user1 = mUserRepository.findUserByUserName(fromUser);
        User user2 = mUserRepository.findUserByUserName(toUser);
        if(user1.getCoin() >= coin) {
            Trade trade1 = new Trade(time,-coin,user2.getUserName(),user1.getUserName(), Const.TRADE,"正常交易");
            Trade trade2 = new Trade(time,coin,user1.getUserName(),user2.getUserName(), Const.TRADE,"正常交易");
            user1.setLastModifyTime(time);
            user2.setLastModifyTime(time);
            user1.setCoin(user1.getCoin() - coin);
            user2.setCoin(user2.getCoin() + coin);
            mUserRepository.save(user1);
            mUserRepository.save(user2);
            mTradeRepository.save(trade1);
            mTradeRepository.save(trade2);
        }else{
            throw new Exception("金额不足");
        }
    }


}
