package com.example.rig.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class StatisticResponse {

    private String month;
    private Long totalOrderCount;
    private int totalBookCount;
    private BigDecimal totalPurchasedAmount;

}
