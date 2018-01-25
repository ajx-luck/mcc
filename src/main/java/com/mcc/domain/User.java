package com.mcc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

/**
 * Created by B04e on 2017/11/13.
 */
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false,unique = true)
    private String userName;
    @Column(nullable = true, unique = true)
    private String passWord;
    @Column(nullable = false)
    private String email;
    @Column(nullable = true)
    private String profilePicture;
    @Column(nullable = false)
    private Long createTime;
    /**
     * 最后更新时间
     */
    @Column(nullable = false)
    private Long lastModifyTime;
    @Column(nullable = false)
    private Long coin;
    @Column(nullable = false, unique = true)
    private String phone;
    @Column(nullable = false)
    private Integer grade;
    /**
     * 机器id,账号只可以有一种机器
     */
    @Column(nullable = false)
    private String machineId;
    /**
     * 机器剩余存活时间
     */
    @Column(nullable = false)
    private Integer aliveDay;
    /**
     * 我的上线
     */
    @Column(nullable = false)
    private String topUserName;
    /**
     * 推广金额
     */
    @Column(nullable = false)
    private Integer referrerCoin;

    /**
     * 钱包地址
     */
    @Column(nullable = false,unique = true)
    private String walletAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Long lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Long getCoin() {
        return coin;
    }

    public void setCoin(Long coin) {
        this.coin = coin;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public Integer getReferrerCoin() {
        return referrerCoin;
    }

    public void setReferrerCoin(Integer referrerCoin) {
        this.referrerCoin = referrerCoin;
    }

    public String getTopUserName() {
        return topUserName;
    }

    public void setTopUserName(String topUserName) {
        this.topUserName = topUserName;
    }

    public Integer getAliveDay() {
        return aliveDay;
    }

    public void setAliveDay(Integer aliveDay) {
        this.aliveDay = aliveDay;
    }

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }
}
