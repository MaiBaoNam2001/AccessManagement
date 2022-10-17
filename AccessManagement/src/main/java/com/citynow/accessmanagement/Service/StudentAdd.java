package com.citynow.accessmanagement.Service;


import com.citynow.accessmanagement.template.IService;
import lombok.Data;

public interface StudentAdd extends IService<StudentAdd.Input, StudentAdd.Output> {

    @Data
    class Input {
        private String name;
        private int age;
        private String gender;
        private String address;
    }

    @Data
    class Output {
        private Integer id;
        private String name;
        private int age;
        private String gender;
        private String address;
    }

}
