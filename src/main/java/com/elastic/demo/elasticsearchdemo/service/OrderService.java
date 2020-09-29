package com.elastic.demo.elasticsearchdemo.service;

import com.elastic.demo.elasticsearchdemo.dto.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto save(OrderDto orderDto);

    OrderDto update(OrderDto orderDto);

    OrderDto getById(String id);

    List<OrderDto> getByCustomerId(String customerId);

    List<OrderDto> getAll();

    void deleteById(String id);

    void deleteByCustomerId(String customerId);

}
