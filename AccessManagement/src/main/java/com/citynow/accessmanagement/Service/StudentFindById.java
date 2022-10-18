package com.citynow.accessmanagement.Service;

import com.citynow.accessmanagement.template.IService;
import lombok.Data;

public interface StudentFindById extends IService<StudentFindById.Input,StudentFindById.Output> {

    @Data
    class Input{
        private Integer id;
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
