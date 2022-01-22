package com.getir.readingisgood.controller;

import com.getir.readingisgood.dto.CustomerResponseDto;
import com.getir.readingisgood.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @PostMapping
    public ResponseEntity<Void> persistCustomer(Customer customer){

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<CustomerResponseDto> getCustomers(){
        CustomerResponseDto dto = new CustomerResponseDto();

        return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
    }

}
