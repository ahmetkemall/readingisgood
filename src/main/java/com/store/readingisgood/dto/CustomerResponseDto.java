package com.store.readingisgood.dto;

import com.store.readingisgood.model.Customer;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDto {

    private List<Customer> customers;

}
