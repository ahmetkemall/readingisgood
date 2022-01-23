package com.getir.readingisgood.service;

import com.getir.readingisgood.dto.OrderItemRequestDto;
import com.getir.readingisgood.dto.OrderRequestDto;
import com.getir.readingisgood.exception.CustomerNotFoundException;
import com.getir.readingisgood.exception.NoStockException;
import com.getir.readingisgood.mapper.OrderMapper;
import com.getir.readingisgood.model.Book;
import com.getir.readingisgood.model.Order;
import com.getir.readingisgood.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final SequenceGeneratorService sequenceGeneratorService;
    private final OrderItemService orderItemService;
    private final StatisticService statisticService;
    private final BookService bookService;
    private final CustomerService customerService;

    @Transactional
    public void placeOrder(OrderRequestDto orderRequestDto) throws CustomerNotFoundException, NoStockException {
        Long id = sequenceGeneratorService.generateSequence(Order.SEQUENCE_NAME);

        validate(orderRequestDto.getCustomerId());
        updateStock(orderRequestDto.getOrderItems());
        persistOrder(orderRequestDto, id);
        persistItems(orderRequestDto, id);
        updateStatistics(orderRequestDto.getOrderItems());

    }

    private void validate(long customerId) throws CustomerNotFoundException {
        customerService.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    private void updateStock(List<OrderItemRequestDto> orderItems) throws NoStockException {
        for (OrderItemRequestDto dto : orderItems)
            updateStock(dto);
    }

    private void updateStock(OrderItemRequestDto dto) throws NoStockException {
        try {
            bookService.updateStock(dto);
        } catch (OptimisticLockingFailureException e) {
            bookService.updateStock(dto);
        }
    }

    private void updateStatistics(List<OrderItemRequestDto> orderItems) {
        int totalBookCount = orderItems.stream()
                .mapToInt(OrderItemRequestDto::getCount)
                .sum();

        BigDecimal totalPrice = getTotalPrice(orderItems);
        statisticService.updateStatistics(1, totalBookCount, totalPrice);
    }

    private BigDecimal getTotalPrice(List<OrderItemRequestDto> orderItems) {
        List<Long> bookIdList = orderItems.stream()
                .map(OrderItemRequestDto::getBookId).collect(toList());

        Map<Long, Integer> bookIdCountMap = getBookIdCountMap(orderItems);
        List<Book> books = bookService.findByIds(bookIdList);

        return books.stream().map(book -> {
            Integer count = bookIdCountMap.get(book.getId());
            if (count == null)
                return BigDecimal.ZERO;
            return book.getPrice().multiply(BigDecimal.valueOf(count));
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Map<Long, Integer> getBookIdCountMap(List<OrderItemRequestDto> orderItems) {
        Map<Long, Integer> map = new HashMap<>();
        for (OrderItemRequestDto dto : orderItems)
            map.put(dto.getBookId(), dto.getCount());

        return map;
    }

    private void persistItems(OrderRequestDto orderRequestDto, Long id) {
        orderItemService.persistAll(id, orderRequestDto.getOrderItems());
    }

    private void persistOrder(OrderRequestDto orderRequestDto, Long id) {
        Order order = orderMapper.map(orderRequestDto);
        order.setId(id);
        orderRepository.save(order);
    }
}
