package com.citynow.accessmanagement.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private int age;
    private String gender;
    private String address;
}
