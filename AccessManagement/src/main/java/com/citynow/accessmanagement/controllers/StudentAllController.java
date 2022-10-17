package com.citynow.accessmanagement.controllers;


import com.citynow.accessmanagement.Mapper.ModelConverter;
import com.citynow.accessmanagement.Service.StudentAll;
import com.citynow.accessmanagement.DTO.Response.StudentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class StudentAllController {
    private final StudentAll studentAll;
    private final ModelConverter modelConverter;

    @GetMapping("api/students")
    public List<StudentResponse> getAllStudent(@RequestParam Optional<Integer> page){
        StudentAll.Input input = new StudentAll.Input();
        input.setId(page.orElse(0));
        List<StudentAll.Output> outputs =
                studentAll.execute(input);
        return modelConverter.mapAllByMappingType(outputs);
    }

}
