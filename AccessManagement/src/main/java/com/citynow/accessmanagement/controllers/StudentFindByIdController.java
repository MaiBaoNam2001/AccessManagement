package com.citynow.accessmanagement.controllers;

import com.citynow.accessmanagement.Mapper.ModelConverter;
import com.citynow.accessmanagement.Service.StudentFindById;
import com.citynow.accessmanagement.DTO.Response.StudentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class StudentFindByIdController {
    private final StudentFindById studentFindById;
    private final ModelConverter modelConverter;

    @GetMapping("api/students/{id}")
    public StudentResponse findStudentById(@PathVariable Integer id){
        StudentFindById.Input input = new StudentFindById.Input();
        input.setId(id);
        StudentFindById.Output output = studentFindById.execute(input);
        return modelConverter.map(output,StudentResponse.class);
    }
}
