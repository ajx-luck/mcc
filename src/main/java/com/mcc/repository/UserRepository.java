package com.mcc.repository;

import com.mcc.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by B04e on 2017/11/24.
 */
public interface UserRepository extends JpaRepository<User,Long>{

}
