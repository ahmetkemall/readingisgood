package com.getir.readingisgood.controller;

import com.getir.readingisgood.dto.CustomerRequestDto;
import com.getir.readingisgood.dto.CustomerResponseDto;
import com.getir.readingisgood.exception.EmailAddressExistsException;
import com.getir.readingisgood.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public void persistCustomer(@RequestBody @Validated CustomerRequestDto customerRequestDto)
            throws EmailAddressExistsException {
        customerService.persist(customerRequestDto);
        ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<CustomerResponseDto> getCustomers() {
        CustomerResponseDto dto = customerService.findAll();
        return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
    }

}
