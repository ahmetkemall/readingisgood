package com.store.readingisgood.controller;

import com.store.readingisgood.dto.OrderDetailResponseDto;
import com.store.readingisgood.dto.OrderPlaceResponseDto;
import com.store.readingisgood.dto.OrderRequestDto;
import com.store.readingisgood.exception.CustomerNotFoundException;
import com.store.readingisgood.exception.InvalidRequestException;
import com.store.readingisgood.exception.NoStockException;
import com.store.readingisgood.exception.NotFoundException;
import com.store.readingisgood.service.OrderService;
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
    public ResponseEntity<OrderPlaceResponseDto> placeOrder(@RequestBody @Valid OrderRequestDto orderRequestDto)
            throws CustomerNotFoundException, NoStockException, InvalidRequestException {
        OrderPlaceResponseDto dto = orderService.placeOrder(orderRequestDto);
        return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDetailResponseDto> getOrderDetail(@PathVariable Long orderId) throws NotFoundException {
        OrderDetailResponseDto dto = orderService.findById(orderId);
        return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderDetailResponseDto>> getCustomerOrders(@PathVariable Long customerId) {
        return new ResponseEntity<>(orderService.findByCustomerId(customerId), HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<OrderDetailResponseDto>> getOrders() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.ACCEPTED);
    }


}
