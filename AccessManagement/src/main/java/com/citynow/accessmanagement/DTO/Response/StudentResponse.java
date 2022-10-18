package com.citynow.accessmanagement.DTO.Response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class StudentResponse {
    private Integer id;
    private String name;
    private int age;
    private String gender;
    private String address;
}
