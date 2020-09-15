package com.elastic.demo.elasticsearchdemo.service;

import com.elastic.demo.elasticsearchdemo.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    CustomerDto save(CustomerDto customerDto);

    CustomerDto update(CustomerDto customerDto);

    CustomerDto getById(String id);

    List<CustomerDto> getAll();

    void deleteById(String id);

    void deleteAll();
}
