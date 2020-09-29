package com.elastic.demo.elasticsearchdemo.service.impl;

import com.elastic.demo.elasticsearchdemo.dto.OrderDto;
import com.elastic.demo.elasticsearchdemo.entity.Order;
import com.elastic.demo.elasticsearchdemo.mapper.OrderMapper;
import com.elastic.demo.elasticsearchdemo.repository.OrderRepository;
import com.elastic.demo.elasticsearchdemo.service.CustomerService;
import com.elastic.demo.elasticsearchdemo.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.elastic.demo.elasticsearchdemo.constant.ExceptionMessageConstant.CUSTOMER_NOT_FOUND_MSG;
import static com.elastic.demo.elasticsearchdemo.constant.ExceptionMessageConstant.ORDER_NOT_FOUND_MSG;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerService customerService;

    @Override
    public OrderDto save(OrderDto orderDto) {
        orderDto.setId(null);
        log.info("Saving Order {}", orderDto);
        validateOrderBeforeSave(orderDto);
        Order order = orderRepository.save(orderMapper.toEntity(orderDto));
        log.info("Saved Order {}", order);
        return orderMapper.toDto(order);
    }

    @Override
    public OrderDto update(OrderDto orderDto) {
        log.info("Updating Order {}", orderDto);
        validateOrderBeforeUpdate(orderDto);
        Order order = orderRepository.save(orderMapper.toEntity(orderDto));
        log.info("Updated Order {}", order);
        return orderMapper.toDto(order);
    }

    @Override
    public OrderDto getById(String id) {
        log.info("Get Order By Id {}", id);
        Order order = orderRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ORDER_NOT_FOUND_MSG.concat(id)));
        log.info("Got Oder {} For The Id {}", order, id);
        return orderMapper.toDto(order);
    }

    @Override
    public List<OrderDto> getByCustomerId(String customerId) {
        log.info("Get Orders By customer Id {}", customerId);
        checkCustomerAvailableOrNot(customerId);
        return orderRepository
                .findByCustomerId(customerId)
                .stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getAll() {
        log.info("Get All Orders");
        List<OrderDto> orders = new ArrayList<>();
        orderRepository
                .findAll()
                .forEach(order -> orders.add(orderMapper.toDto(order)));
        return orders;
    }

    @Override
    public void deleteById(String id) {
        log.info("Delete Order By Id {}", id);
        checkOrderAvailableOrNot(id);
        orderRepository.deleteById(id);
    }

    @Override
    public void deleteByCustomerId(String customerId) {
        log.info("Delete Order By Customer Id {}", customerId);
        checkCustomerAvailableOrNot(customerId);
        orderRepository.deleteByCustomerId(customerId);
    }

    private void validateOrderBeforeSave(OrderDto orderDto) {
        checkCustomerAvailableOrNot(orderDto.getCustomerId());
    }

    private void validateOrderBeforeUpdate(OrderDto orderDto) {
        if (StringUtils.isBlank(orderDto.getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "To Update Order Please Provide Order Id");
        checkOrderAvailableOrNot(orderDto.getId());
        checkCustomerAvailableOrNot(orderDto.getCustomerId());
    }

    private void checkCustomerAvailableOrNot(String customerId) {
        if (!customerService.isCustomerAvailable(customerId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, CUSTOMER_NOT_FOUND_MSG.concat(customerId));
    }

    private void checkOrderAvailableOrNot(String orderId) {
        if (!orderRepository.existsById(orderId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ORDER_NOT_FOUND_MSG.concat(orderId));
    }

}
