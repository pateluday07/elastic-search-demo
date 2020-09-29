package com.elastic.demo.elasticsearchdemo.repository;

import com.elastic.demo.elasticsearchdemo.entity.Order;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends ElasticsearchRepository<Order, String> {

    List<Order> findByCustomerId(String customerId);

    void deleteByCustomerId(String customerId);
}
