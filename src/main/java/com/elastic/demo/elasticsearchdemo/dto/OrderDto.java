package com.elastic.demo.elasticsearchdemo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class OrderDto {

    private String id;
    @NotBlank(message = "Please Enter Customer Id")
    private String customerId;
    @NotBlank(message = "Please Enter Order Title")
    private String orderTitle;
    private String orderDetail;
}
