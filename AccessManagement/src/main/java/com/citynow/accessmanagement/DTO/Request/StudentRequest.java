package com.citynow.accessmanagement.DTO.Request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class StudentRequest {
    private Integer id;
    @NotBlank
    private String name;
    @Min(1)
    private int age;
    @NotBlank
    private String gender;
    @NotBlank
    private String address;
}
