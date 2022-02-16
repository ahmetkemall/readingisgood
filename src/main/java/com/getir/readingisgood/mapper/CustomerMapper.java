package com.getir.readingisgood.mapper;


import com.getir.readingisgood.dto.CustomerRequestDto;
import com.getir.readingisgood.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer map(CustomerRequestDto dto);
}
