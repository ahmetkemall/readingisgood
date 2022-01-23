package com.getir.readingisgood.service;


import com.getir.readingisgood.dto.OrderItemRequestDto;
import com.getir.readingisgood.mapper.OrderItemMapper;
import com.getir.readingisgood.model.OrderItem;
import com.getir.readingisgood.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemMapper orderItemMapper;
    private final OrderItemRepository orderItemRepository;
    private final SequenceGeneratorService sequenceGeneratorService;

    public void persistAll(Long id, List<OrderItemRequestDto> dtoList) {
        dtoList.forEach(dto -> persist(id, dto));
    }

    public void persist(Long orderId, OrderItemRequestDto dto) {
        OrderItem orderItem = orderItemMapper.map(dto);
        orderItem.setId(sequenceGeneratorService.generateSequence(OrderItem.SEQUENCE_NAME));
        orderItem.setOrderId(orderId);
        orderItemRepository.save(orderItem);
    }

}
