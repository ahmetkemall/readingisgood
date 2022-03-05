package com.store.readingisgood.mapper;

import com.store.readingisgood.dto.OrderDetailResponseDto;
import com.store.readingisgood.dto.OrderRequestDto;
import com.store.readingisgood.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order map(OrderRequestDto dto);

    OrderDetailResponseDto map(Order order);
}
