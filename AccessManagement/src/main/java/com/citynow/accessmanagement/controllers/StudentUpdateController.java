package com.citynow.accessmanagement.controllers;

import com.citynow.accessmanagement.Service.StudentUpdate;
import com.citynow.accessmanagement.DTO.Request.StudentRequest;
import com.citynow.accessmanagement.DTO.Response.StudentResponse;
import com.citynow.accessmanagement.Mapper.ModelConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StudentUpdateController {
    private final StudentUpdate studentUpdate;
    private final ModelConverter modelConverter;

    @PutMapping("api/students")
    public StudentResponse updateStudent(@RequestBody StudentRequest studentRequest){
        StudentUpdate.Input  input = modelConverter.map(studentRequest,StudentUpdate.Input.class);
        StudentUpdate.Output output = studentUpdate.execute(input);
        return  modelConverter.map(output,StudentResponse.class);
    }
}
