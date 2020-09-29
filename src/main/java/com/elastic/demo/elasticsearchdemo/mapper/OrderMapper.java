package com.elastic.demo.elasticsearchdemo.mapper;

import com.elastic.demo.elasticsearchdemo.dto.OrderDto;
import com.elastic.demo.elasticsearchdemo.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toEntity(OrderDto orderDto);

    OrderDto toDto(Order order);

}
