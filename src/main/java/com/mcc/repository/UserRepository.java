package com.mcc.repository;

import com.mcc.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by B04e on 2017/11/24.
 */
public interface UserRepository extends JpaRepository<User,Long>{
    User findByUserName(@Param("userName") String userName);
}
