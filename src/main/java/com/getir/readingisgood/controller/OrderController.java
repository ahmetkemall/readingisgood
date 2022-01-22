package com.getir.readingisgood.controller;

import com.getir.readingisgood.dto.OrderDetailResponseDto;
import com.getir.readingisgood.dto.OrderRequestDto;
import com.getir.readingisgood.dto.OrderResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @PostMapping
    public ResponseEntity<Void> placeOrder(OrderRequestDto orderRequestDto) {
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDetailResponseDto> getOrderDetail(@PathVariable Long orderId){
        OrderDetailResponseDto dto = OrderDetailResponseDto.builder().id(orderId).build();
        return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<OrderResponseDto> getOrders(){
        OrderResponseDto dto = new OrderResponseDto();
        return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
    }


}
