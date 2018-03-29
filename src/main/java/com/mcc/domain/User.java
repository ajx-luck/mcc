package com.mcc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

/**
 * Created by B04e on 2017/11/13.
 */
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false,unique = true)
    private String userName;
    /**
     * 明文保存，也许会有用
     */
    @Column(nullable = true)
    private String passWord;
    /**
     * 支付密码
     */
    @Column(nullable = false)
    private String payWord;
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
    /**
     * 货币单位，存储为整数，实际显示除1,000,000
     */
    @Column(nullable = false)
    private Long coin;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private Integer grade;
    /**
     * 机器id,账号只可以有一种机器
     */
    @Column(nullable = false)
    private Long machineId;
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
    private Long referrerCoin;

    /**
     * 钱包地址
     */
    @Column(nullable = false,unique = true)
    private String walletAddress;
    /**
     * 钱包私钥，跟地址唯一对应
     */
    @Column(nullable = true)
    private String walletKey;
    /**
     * 上一次的密码，明文保存，也许会有用
     */
    @Column(nullable = true)
    private String prePassWord;
    /**
     * 登陆的tonken
     */
    @Column(nullable = true)
    private String tonken;
    /**
     * 关联ids,可以存在多个，用','作分割
     */
    private String contactIds;

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

    public Long getMachineId() {
        return machineId;
    }

    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }

    public Long getReferrerCoin() {
        return referrerCoin;
    }

    public void setReferrerCoin(Long referrerCoin) {
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

    public String getWalletKey() {
        return walletKey;
    }

    public void setWalletKey(String walletKey) {
        this.walletKey = walletKey;
    }

    public String getPrePassWord() {
        return prePassWord;
    }

    public void setPrePassWord(String prePassWord) {
        this.prePassWord = prePassWord;
    }

    public String getTonken() {
        return tonken;
    }

    public void setTonken(String tonken) {
        this.tonken = tonken;
    }

    public String getPayWord() {
        return payWord;
    }

    public void setPayWord(String payWord) {
        this.payWord = payWord;
    }

    public String getContactIds() {
        return contactIds;
    }

    public void setContactIds(String contactIds) {
        this.contactIds = contactIds;
    }
}
