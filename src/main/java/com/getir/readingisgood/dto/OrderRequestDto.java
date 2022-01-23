package com.getir.readingisgood.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderRequestDto {

    @NotNull
    private Long customerId;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    private List<@Valid OrderItemRequestDto> orderItems;

}
