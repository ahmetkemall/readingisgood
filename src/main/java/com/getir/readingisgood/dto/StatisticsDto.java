package com.getir.readingisgood.dto;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.Date;

public class StatisticsDto {

    @Id
    private Date date;

    private int totalOrderCount;
    private int totalBookCount;
    private BigDecimal totalAmount;

}
