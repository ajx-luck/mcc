package com.mcc.repository;

import com.mcc.domain.Trade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by B04e on 2017/11/29.
 */
@Repository
public interface TradeRepository extends JpaRepository<Trade,Long>,PagingAndSortingRepository<Trade, Long> {

    @Query("select c from Trade c where c.userName = ?1")
    Page<Trade> findAllTradesByUserName(String userName, Pageable pageable);
}
