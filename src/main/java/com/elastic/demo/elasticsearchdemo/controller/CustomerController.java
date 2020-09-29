package com.elastic.demo.elasticsearchdemo.controller;

import com.elastic.demo.elasticsearchdemo.dto.CustomerDto;
import com.elastic.demo.elasticsearchdemo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public CustomerDto save(@Valid @RequestBody CustomerDto customerDto) {
        log.info("Rest API To Save Customer {}", customerDto);
        return customerService.save(customerDto);
    }

    @PutMapping
    public CustomerDto update(@Valid @RequestBody CustomerDto customerDto) {
        log.info("Rest API To Update Customer {}", customerDto);
        return customerService.update(customerDto);
    }

    @GetMapping("/id/{id}")
    public CustomerDto getById(@PathVariable String id) {
        log.info("Rest API To Get Customer By Id {}", id);
        return customerService.getById(id);
    }

    @GetMapping
    public List<CustomerDto> getAll() {
        log.info("Rest API To Get All Customer");
        return customerService.getAll();
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable String id) {
        log.info("Rest API To Delete Customer By Id {}", id);
        customerService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAll() {
        log.info("Rest API To Delete All Customer");
        customerService.deleteAll();
        return ResponseEntity.ok().build();
    }
}
