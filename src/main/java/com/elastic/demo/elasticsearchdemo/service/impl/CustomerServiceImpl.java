package com.elastic.demo.elasticsearchdemo.service.impl;

import com.elastic.demo.elasticsearchdemo.dto.CustomerDto;
import com.elastic.demo.elasticsearchdemo.entity.Customer;
import com.elastic.demo.elasticsearchdemo.mapper.CustomerMapper;
import com.elastic.demo.elasticsearchdemo.repository.CustomerRepository;
import com.elastic.demo.elasticsearchdemo.repository.OrderRepository;
import com.elastic.demo.elasticsearchdemo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static com.elastic.demo.elasticsearchdemo.constant.ExceptionMessageConstant.CUSTOMER_NOT_FOUND_MSG;

@Service
@Log4j2
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final OrderRepository orderRepository;

    @Override
    public CustomerDto save(CustomerDto customerDto) {
        customerDto.setId(null);
        log.info("Saving Customer {}", customerDto);
        Customer customer = customerRepository.save(customerMapper.toEntity(customerDto));
        log.info("Saved Customer {}", customer);
        return customerMapper.toDto(customer);
    }

    @Override
    public CustomerDto update(CustomerDto customerDto) {
        log.info("Updating Customer {}", customerDto);
        validateCustomerBeforeUpdate(customerDto);
        return customerMapper
                .toDto(customerRepository
                        .save(customerMapper.toEntity(customerDto)));
    }

    @Override
    public CustomerDto getById(String id) {
        log.info("Get Customer For The Id {}", id);
        checkCustomerAvailableForTheGivenId(id);
        return customerMapper
                .toDto(customerRepository
                        .findById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                CUSTOMER_NOT_FOUND_MSG.concat(id))));
    }

    @Override
    public List<CustomerDto> getAll() {
        log.info("Get All Customers");
        List<CustomerDto> customerDtos = new ArrayList<>();
        customerRepository
                .findAll()
                .forEach(customer -> customerDtos.add(customerMapper.toDto(customer)));
        return customerDtos;
    }

    @Override
    public void deleteById(String id) {
        log.info("Deleting Customer For The Id {}", id);
        checkCustomerAvailableForTheGivenId(id);
        customerRepository.deleteById(id);
        orderRepository.deleteByCustomerId(id);
    }

    @Override
    public void deleteAll() {
        log.info("Delete All Customers");
        customerRepository.deleteAll();
        orderRepository.deleteAll();
    }

    @Override
    public boolean isCustomerAvailable(String id) {
        return customerRepository.existsById(id);
    }

    private void validateCustomerBeforeUpdate(CustomerDto customerDto) {
        if (StringUtils.isBlank(customerDto.getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please Enter Id To Update Customer");
        checkCustomerAvailableForTheGivenId(customerDto.getId());
    }

    private void checkCustomerAvailableForTheGivenId(String id) {
        if (!isCustomerAvailable(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, CUSTOMER_NOT_FOUND_MSG.concat(id));
    }

}
