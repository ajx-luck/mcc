package com.mcc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by B04e on 2018/1/24.
 */
@Entity
public class Machine {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false,unique = true)
    private String machineName;
    @Column(nullable = false)
    private Long outCoin;
    @Column(nullable = false)
    private Long price;
    @Column(nullable = false)
    private String machineDesc;
    @Column(nullable = false)
    private Long createTime;



    public Long getOutCoin() {
        return outCoin;
    }

    public void setOutCoin(Long outCoin) {
        this.outCoin = outCoin;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getMachineDesc() {
        return machineDesc;
    }

    public void setMachineDesc(String machineDesc) {
        this.machineDesc = machineDesc;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }


}
