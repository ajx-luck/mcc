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
    private Integer tradeCoin;
    @Column(nullable = false,unique = true)
    private String tradeName;
    @Column(nullable = false,unique = true)
    private String userName;
}
