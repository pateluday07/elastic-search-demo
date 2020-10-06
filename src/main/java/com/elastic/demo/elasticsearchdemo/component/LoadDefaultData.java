package com.elastic.demo.elasticsearchdemo.component;

import com.elastic.demo.elasticsearchdemo.entity.Customer;
import com.elastic.demo.elasticsearchdemo.entity.Order;
import com.elastic.demo.elasticsearchdemo.repository.CustomerRepository;
import com.elastic.demo.elasticsearchdemo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Log4j2
@RequiredArgsConstructor
public class LoadDefaultData implements ApplicationRunner {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Customer customer = new Customer();
        customer.setFirstName("Uday");
        customer.setLastName("Patel");
        customer = customerRepository.save(customer);
        log.info("Default Customer Successfully Saved {}", customer);
        Order order = new Order();
        order.setCustomerId(customer.getId());
        order.setOrderTitle("Nike Shoes");
        order = orderRepository.save(order);
        log.info("Default Order Successfully Saved {}", order);
    }
}
