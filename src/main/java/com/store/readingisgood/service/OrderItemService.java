package com.store.readingisgood.service;


import com.store.readingisgood.dto.OrderItemDetailResponseDto;
import com.store.readingisgood.dto.OrderItemRequestDto;
import com.store.readingisgood.mapper.OrderItemMapper;
import com.store.readingisgood.model.OrderItem;
import com.store.readingisgood.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

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

    public List<OrderItemDetailResponseDto> findByOrderItem(Long orderId) {
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);
        return orderItems.stream().map(orderItemMapper::map).collect(toList());
    }
}
