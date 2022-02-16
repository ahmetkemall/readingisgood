package com.getir.readingisgood.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class OrderResponseDto {

    private long customerId;

    private BigDecimal totalAmount;

    private Date startDate;

    private Date endDate;

    private long orderLongUx;

}
