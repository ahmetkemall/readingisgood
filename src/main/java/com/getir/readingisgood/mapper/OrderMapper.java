package com.getir.readingisgood.mapper;

import com.getir.readingisgood.dto.OrderRequestDto;
import com.getir.readingisgood.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order map(OrderRequestDto dto);
}
