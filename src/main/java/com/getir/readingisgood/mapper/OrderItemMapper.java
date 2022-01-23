package com.getir.readingisgood.mapper;

import com.getir.readingisgood.dto.OrderItemRequestDto;
import com.getir.readingisgood.model.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItem map(OrderItemRequestDto dto);
}
