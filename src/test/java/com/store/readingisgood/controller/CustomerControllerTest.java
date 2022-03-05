package com.store.readingisgood.controller;

import com.store.readingisgood.dto.CustomerRequestDto;
import com.store.readingisgood.dto.CustomerResponseDto;
import com.store.readingisgood.service.CustomerService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class CustomerControllerTest {

    @InjectMocks
    private CustomerController controller;

    @Mock
    private CustomerService customerService;

    @Test
    @SneakyThrows
    public void shouldPersistCustomer() {
        CustomerRequestDto dto = CustomerRequestDto.builder()
                .name("name")
                .email("email")
                .build();

        controller.persistCustomer(dto);

        verify(customerService).persist(dto);
    }

    @Test
    public void shouldGetCustomers() {
        CustomerResponseDto dto = mock(CustomerResponseDto.class);
        when(customerService.findAll()).thenReturn(dto);

        ResponseEntity<CustomerResponseDto> response = controller.getCustomers();
        assertEquals(dto, response.getBody());
    }

}