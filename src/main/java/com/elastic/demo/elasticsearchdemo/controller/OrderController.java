package com.elastic.demo.elasticsearchdemo.controller;

import com.elastic.demo.elasticsearchdemo.dto.OrderDto;
import com.elastic.demo.elasticsearchdemo.service.OrderService;
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
@RequestMapping("/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderDto save(@Valid @RequestBody OrderDto orderDto){
        log.info("Rest API To Save Order {}", orderDto);
        return orderService.save(orderDto);
    }

    @PutMapping
    public OrderDto update(@Valid @RequestBody OrderDto orderDto){
        log.info("Update API To Save Order {}", orderDto);
        return orderService.update(orderDto);
    }

    @GetMapping("/id/{id}")
    public OrderDto getById(@PathVariable String id){
        log.info("Rest API To Get Order By Id {}", id);
        return orderService.getById(id);
    }

    @GetMapping("/customers/id/{id}")
    public List<OrderDto> getByCustomerId(@PathVariable String id){
        log.info("Rest API To Get Order By Customer Id {}", id);
        return orderService.getByCustomerId(id);
    }

    @GetMapping
    public List<OrderDto> getAll(){
        log.info("Rest API To Get All Orders");
        return orderService.getAll();
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable String id){
        log.info("Rest API To Delete Order By Id {}", id);
        orderService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/customers/id/{id}")
    public ResponseEntity<HttpStatus> deleteByCustomerId(@PathVariable String id){
        log.info("Rest API To Delete Order By Customer Id {}", id);
        orderService.deleteByCustomerId(id);
        return ResponseEntity.ok().build();
    }
}
