package com.elastic.demo.elasticsearchdemo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "customer")
@Getter
@Setter
@ToString
public class Customer {

    @Id
    private String id;
    private String firstName;
    private String lastName;

}
