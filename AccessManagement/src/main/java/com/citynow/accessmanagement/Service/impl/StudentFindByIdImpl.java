package com.citynow.accessmanagement.Service.impl;

import com.citynow.accessmanagement.Mapper.ModelConverter;
import com.citynow.accessmanagement.Service.StudentFindById;
import com.citynow.accessmanagement.repositories.StudentRepository;
import com.citynow.accessmanagement.template.BaseService;
import com.citynow.accessmanagement.Entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class StudentFindByIdImpl extends BaseService<StudentFindById.Input,StudentFindById.Output> implements StudentFindById {

    private final StudentRepository studentRepository;

    @Override
    protected void preExecute(Input input) {

    }

    @Override
    protected Output doExecute(Input input) {
        Student studentFound = studentRepository.findById(input.getId()).orElseThrow();
        return new ModelConverter().map(studentFound,StudentFindById.Output.class);
    }

    @Override
    protected void postExecute(Input input) {

    }
}
