package com.mcc.service.impl;

import com.alibaba.fastjson.JSON;
import com.mcc.comm.Const;
import com.mcc.domain.Machine;
import com.mcc.domain.Trade;
import com.mcc.domain.User;
import com.mcc.repository.MachineRepository;
import com.mcc.repository.TradeRepository;
import com.mcc.repository.UserRepository;
import com.mcc.service.CoinService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by B04e on 2018/1/16.
 */
@Service
public class CoinServiceImpl implements CoinService {

    @Autowired
    UserRepository mUserRepository;
    @Autowired
    TradeRepository mTradeRepository;
    @Autowired
    MachineRepository mMachineRepository;
    List<Machine> mMachines;
    Map<Long,Machine> mMachineMap;
    long time;

    /**
     * 定时任务
     */
    @Scheduled(cron="0 0 0 * * ?")
    @Transactional(value="transactionManager", rollbackFor = Exception.class)
    @Override
    public void addCoin(){
        initMachine();
        int page = 0;
        time = new Date().getTime();
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

    /**
     * 初始化矿机资源
     */
    private void initMachine() {
        mMachineMap = new HashMap<>();
        mMachines = mMachineRepository.findAll();
        for(Machine machine : mMachines){
            mMachineMap.put(machine.getId(),machine);
        }
    }

    /**
     * 分页新增实现
     * @param userPage
     */
    private void addCoinImpl(Page<User> userPage){
        for(User user:userPage){
            if(user.getAliveDay() > 0){
                long coin = mMachineMap.get(user.getMachineId()).getOutCoin();
                user.setCoin(user.getCoin() + coin);
                user.setLastModifyTime(time);
                Trade trade = new Trade(time,coin,"矿机收益",user.getUserName(), Const.SYSTEM_ADD,"正常收益");
                mUserRepository.save(user);
                mTradeRepository.save(trade);
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
