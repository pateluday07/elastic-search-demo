package com.elastic.demo.elasticsearchdemo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "order", shards = 2)
@Getter
@Setter
@ToString
public class Order {

    @Id
    private String id;
    private String customerId;
    private String orderTitle;
    private String orderDetail;
}
