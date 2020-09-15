package com.elastic.demo.elasticsearchdemo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CustomerDto {

    private String id;
    @NotBlank(message = "Please Enter First Name")
    private String firstName;
    @NotBlank(message = "Please Enter Last Name")
    private String lastName;
}
