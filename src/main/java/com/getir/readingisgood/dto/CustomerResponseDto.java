package com.getir.readingisgood.dto;

import com.getir.readingisgood.model.Customer;
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
