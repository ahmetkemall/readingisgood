package com.store.readingisgood.dto;


import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailResponseDto {

    private long id;

    private long customerId;

    private Date startDate;

    private Date endDate;

    private long orderLongUx;

    private List<OrderItemDetailResponseDto> orderItems;

}
