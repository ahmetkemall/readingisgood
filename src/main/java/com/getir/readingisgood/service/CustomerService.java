package com.getir.readingisgood.service;

import com.getir.readingisgood.dto.CustomerRequestDto;
import com.getir.readingisgood.dto.CustomerResponseDto;
import com.getir.readingisgood.exception.EmailAddressExistsException;
import com.getir.readingisgood.mapper.CustomerMapper;
import com.getir.readingisgood.model.Customer;
import com.getir.readingisgood.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final SequenceGeneratorService sequenceGeneratorService;

    public void persist(CustomerRequestDto customerRequestDto) throws EmailAddressExistsException {
        try {
            Customer newCustomer = customerMapper.map(customerRequestDto);
            newCustomer.setId(sequenceGeneratorService.generateSequence(Customer.SEQUENCE_NAME));
            customerRepository.save(newCustomer);
        } catch (DuplicateKeyException e) {
            throw new EmailAddressExistsException();
        }
    }

    public CustomerResponseDto findAll() {
        return CustomerResponseDto.builder()
                .customers(customerRepository.findAll())
                .build();
    }

    public Optional<Customer> findById(long customerId) {
        return customerRepository.findById(customerId);
    }
}
