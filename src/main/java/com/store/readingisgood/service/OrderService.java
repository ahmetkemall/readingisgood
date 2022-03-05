package com.store.readingisgood.service;

import com.store.readingisgood.dto.OrderDetailResponseDto;
import com.store.readingisgood.dto.OrderItemRequestDto;
import com.store.readingisgood.dto.OrderPlaceResponseDto;
import com.store.readingisgood.dto.OrderRequestDto;
import com.store.readingisgood.exception.CustomerNotFoundException;
import com.store.readingisgood.exception.InvalidRequestException;
import com.store.readingisgood.exception.NoStockException;
import com.store.readingisgood.exception.NotFoundException;
import com.store.readingisgood.mapper.OrderMapper;
import com.store.readingisgood.model.Book;
import com.store.readingisgood.model.Order;
import com.store.readingisgood.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public OrderPlaceResponseDto placeOrder(OrderRequestDto orderRequestDto) throws CustomerNotFoundException, NoStockException, InvalidRequestException {
        Long id = sequenceGeneratorService.generateSequence(Order.SEQUENCE_NAME);

        validate(orderRequestDto);
        updateStock(orderRequestDto.getOrderItems());
        persistOrder(orderRequestDto, id);
        persistItems(orderRequestDto, id);
        updateStatistics(orderRequestDto.getOrderItems());

        return OrderPlaceResponseDto.builder().orderId(id).build();
    }

    private void validate(OrderRequestDto dto) throws CustomerNotFoundException, InvalidRequestException {
        if(dto.getEndDate().before(dto.getStartDate())
            || dto.getEndDate().equals(dto.getStartDate()))
            throw new InvalidRequestException("EndDate should be after the StartDate");

        customerService.findById(dto.getCustomerId()).orElseThrow(() -> new CustomerNotFoundException(dto.getCustomerId()));
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
        updateStatistics(totalBookCount, totalPrice);
    }

    private void updateStatistics(int totalBookCount, BigDecimal totalPrice) {
        try {
            statisticService.updateStatistics(1, totalBookCount, totalPrice);
        } catch (OptimisticLockingFailureException e) {
            statisticService.updateStatistics(1, totalBookCount, totalPrice);
        }
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
        order.setOrderLongUx(order.getUnixDateDiff());
        orderRepository.save(order);
    }

    public OrderDetailResponseDto findById(Long orderId) throws NotFoundException {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException(orderId));
        return convertOrder(order);
    }

    private OrderDetailResponseDto convertOrder(Order order) {
        OrderDetailResponseDto orderDetailResponseDto = orderMapper.map(order);
        orderDetailResponseDto.setOrderItems(orderItemService.findByOrderItem(order.getId()));
        return orderDetailResponseDto;
    }

    public List<OrderDetailResponseDto> findAll(){
        List<Order> orders = orderRepository.findAllByOrderByOrderLongUxAsc();

        return orders.stream()
                .map(this::convertOrder)
                .collect(Collectors.toList());
    }

    public List<OrderDetailResponseDto>  findByCustomerId(Long customerId) {
        List<Order> orders = orderRepository.findByCustomerId(customerId);

        return orders.stream()
                .map(this::convertOrder)
                .collect(Collectors.toList());
    }
}
