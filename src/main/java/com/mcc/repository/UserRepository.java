package com.mcc.repository;

import com.mcc.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by B04e on 2017/11/24.
 */
public interface UserRepository extends JpaRepository<User,Long>{
    User findById(Long id);

    User findByUserName(String userName);

    User findByUserNameOrEmail(String username, String email);

    User findByEmail(String email);
}
