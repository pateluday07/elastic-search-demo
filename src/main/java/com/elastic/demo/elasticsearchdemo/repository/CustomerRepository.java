package com.elastic.demo.elasticsearchdemo.repository;

import com.elastic.demo.elasticsearchdemo.entity.Customer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ElasticsearchRepository<Customer, String> {
}
