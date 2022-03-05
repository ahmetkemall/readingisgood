package com.store.readingisgood.mapper;


import com.store.readingisgood.dto.CustomerRequestDto;
import com.store.readingisgood.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer map(CustomerRequestDto dto);
}
