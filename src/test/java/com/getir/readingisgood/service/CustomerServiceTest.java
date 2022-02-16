package com.getir.readingisgood.service;

import com.getir.readingisgood.dto.CustomerRequestDto;
import com.getir.readingisgood.dto.CustomerResponseDto;
import com.getir.readingisgood.exception.EmailAddressExistsException;
import com.getir.readingisgood.mapper.CustomerMapper;
import com.getir.readingisgood.model.Customer;
import com.getir.readingisgood.repository.CustomerRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class CustomerServiceTest {

    @InjectMocks
    private CustomerService service;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @Mock
    private SequenceGeneratorService sequenceGeneratorService;
    
    @Test
    @SneakyThrows
    void shouldPersist() {
        CustomerRequestDto dto = CustomerRequestDto.builder()
                .email("someEmail@email.com")
                .name("some name")
                .build();

        Customer customer = new Customer();
        when(customerMapper.map(dto)).thenReturn(customer);
        when(sequenceGeneratorService.generateSequence(Customer.SEQUENCE_NAME)).thenReturn(3L);

        service.persist(dto);


        ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository).save(captor.capture());

        assertEquals(3L, captor.getValue().getId());
    }


    @Test
    void shouldThrowException() {
        CustomerRequestDto dto = CustomerRequestDto.builder()
                .email("someEmail@email.com")
                .name("some name")
                .build();

        Customer customer = new Customer();
        when(customerMapper.map(dto)).thenReturn(customer);
        when(sequenceGeneratorService.generateSequence(Customer.SEQUENCE_NAME)).thenReturn(3L);
        when(customerRepository.save(any())).thenThrow(new DuplicateKeyException(""));
        try {
            service.persist(dto);
        } catch (EmailAddressExistsException e) {
            //ok
        } catch (Exception ex){
            fail();
        }
    }

    @Test
    void shouldFindAll() {
        List mockList = mock(List.class);
        when(customerRepository.findAll()).thenReturn(mockList);

        CustomerResponseDto dto = service.findAll();

        assertEquals(mockList, dto.getCustomers());
    }
}