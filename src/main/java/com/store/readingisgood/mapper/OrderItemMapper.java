package com.store.readingisgood.mapper;

import com.store.readingisgood.dto.OrderItemDetailResponseDto;
import com.store.readingisgood.dto.OrderItemRequestDto;
import com.store.readingisgood.model.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItem map(OrderItemRequestDto dto);

    OrderItemDetailResponseDto map(OrderItem orderItem);
}
