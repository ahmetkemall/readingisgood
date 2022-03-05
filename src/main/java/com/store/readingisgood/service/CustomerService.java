package com.store.readingisgood.service;

import com.store.readingisgood.dto.CustomerRequestDto;
import com.store.readingisgood.dto.CustomerResponseDto;
import com.store.readingisgood.exception.EmailAddressExistsException;
import com.store.readingisgood.mapper.CustomerMapper;
import com.store.readingisgood.model.Customer;
import com.store.readingisgood.repository.CustomerRepository;
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
