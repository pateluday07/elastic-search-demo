package com.elastic.demo.elasticsearchdemo.mapper;

import com.elastic.demo.elasticsearchdemo.dto.CustomerDto;
import com.elastic.demo.elasticsearchdemo.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toEntity(CustomerDto customerDto);

    CustomerDto toDto(Customer customer);
}
