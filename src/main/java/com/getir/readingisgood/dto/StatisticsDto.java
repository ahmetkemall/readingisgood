package com.getir.readingisgood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsDto {

    private String date;
    private int totalOrderCount;
    private int totalBookCount;
    private BigDecimal totalAmount;


}
