package com.getir.readingisgood.dto;

import com.getir.readingisgood.model.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderResponseDto {

    private List<Order> orderList;
}
