package com.mcc.repository;

import com.mcc.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by B04e on 2017/11/24.
 */
public interface UserRepository extends JpaRepository<User,Long>{
    User findUserByUserName(@Param("userName") String userName);
    User findUserByUserNameAndPassWord(@Param("userName") String userName,@Param("passWord") String passWord);
    User findUserByWalletAddress(@Param("walletAddress") String walletAddress);
    User findUserById(@Param("id") Long id);
    List<User> findUsersByIdIn(@Param("ids") List<Long> ids);
}
