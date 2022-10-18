package com.citynow.accessmanagement.controllers;


import com.citynow.accessmanagement.Service.StudentDelete;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StudentDeleteController {

    private final StudentDelete studentDelete;

    @DeleteMapping("api/students/{id}")
    public boolean deleteStudent(@PathVariable Integer id){
       return studentDelete.execute(id);
    }

}
