package com.mcc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by B04e on 2017/11/29.
 */
@Entity
public class Trade {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private Long tradeTime;
    @Column(nullable = false)
    private Long tradeCoin;
    /**
     * 交易来源/去向
     */
    @Column(nullable = false)
    private String tradeName;
    /**
     * 交易所属用户id
     */
    @Column(nullable = false)
    private String userName;
    /**
     * 交易类型
     */
    @Column(nullable = false)
    private Integer tradeType;
    /**
     * 交易描述
     */
    @Column(nullable = false)
    private String tradeDesc;

    public Trade(){

    }

    public Trade(Long tradeTime, Long tradeCoin, String tradeName, String userName, Integer tradeType, String tradeDesc) {
        this.tradeTime = tradeTime;
        this.tradeCoin = tradeCoin;
        this.tradeName = tradeName;
        this.userName = userName;
        this.tradeType = tradeType;
        this.tradeDesc = tradeDesc;
    }

    public Long getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Long tradeTime) {
        this.tradeTime = tradeTime;
    }

    public Long getTradeCoin() {
        return tradeCoin;
    }

    public void setTradeCoin(Long tradeCoin) {
        this.tradeCoin = tradeCoin;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getTradeType() {
        return tradeType;
    }

    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }

    public String getTradeDesc() {
        return tradeDesc;
    }

    public void setTradeDesc(String tradeDesc) {
        this.tradeDesc = tradeDesc;
    }
}
