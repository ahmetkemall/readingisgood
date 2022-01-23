package com.getir.readingisgood.controller;

import com.getir.readingisgood.dto.OrderDetailResponseDto;
import com.getir.readingisgood.dto.OrderRequestDto;
import com.getir.readingisgood.exception.CustomerNotFoundException;
import com.getir.readingisgood.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public void placeOrder(@RequestBody @Valid OrderRequestDto orderRequestDto) throws CustomerNotFoundException {
        orderService.placeOrder(orderRequestDto);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDetailResponseDto> getOrderDetail(@PathVariable Long orderId) {
        OrderDetailResponseDto dto = OrderDetailResponseDto.builder().id(orderId).build();
        return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderDetailResponseDto>> getCustomerOrders(@PathVariable Long customerId) {
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<OrderDetailResponseDto>> getOrders() {
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }


}
