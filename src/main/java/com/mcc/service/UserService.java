package com.mcc.service;

import com.mcc.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by B04e on 2018/1/26.
 */
public interface UserService {

    void createUser(User user);

    User findUserByUserNameAndPsd(String userName,String password);

    User findUserByWalletAddress(String walletAddress);

    User findUserByUserName(String userName);

    User updatePsd(User user,String psd);

    /**
     * 添加联系账号
     * @param user
     * @param userName
     * @param psd
     * @param payPsd
     * @return
     */
    User addContactAccount(User user,String userName,String psd,String payPsd);

    /**
     * 切换账号
     * @param id
     * @return
     */
    User changeUserByContactId(User user,Long id);

    /**
     * 获取所有联系人
     * @param user
     * @return
     */
    List<User> getAllContacts(User user);

    /**
     * 获取推荐的用户
     * @param topUserName
     * @return
     */
    List<User> getTopUser(String topUserName);

    /**
     * 根据cookie获取user对象
     * @param request
     * @return
     */
    User findUserByCookie(HttpServletRequest request);
}
