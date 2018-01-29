package com.mcc.service.impl;

import com.alibaba.fastjson.JSON;
import com.mcc.domain.Trade;
import com.mcc.repository.TradeRepository;
import com.mcc.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Created by B04e on 2018/1/29.
 */
@Service
public class TradeServiceImpl implements TradeService{
    @Autowired
    TradeRepository mTradeRepository;
    @Override
    public String findAllByUserName(String userName, int page) {
        Pageable pageable = new PageRequest(page,10, Sort.Direction.DESC, "id");
        Page<Trade> trades = mTradeRepository.findAllTradesByUserName(userName,pageable);
        return JSON.toJSONString(trades);
    }
}
