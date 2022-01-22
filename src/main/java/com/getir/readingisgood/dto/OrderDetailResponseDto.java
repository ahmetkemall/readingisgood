package com.getir.readingisgood.dto;


import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailResponseDto {

    private long id;

    private long customerId;

    private BigDecimal totalAmount;

    private Date startDate;

    private Date endDate;

    private long orderLongUx;

}
