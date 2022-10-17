package com.citynow.accessmanagement.Service.impl;

import com.citynow.accessmanagement.Service.StudentUpdate;
import com.citynow.accessmanagement.Entity.Student;
import com.citynow.accessmanagement.Mapper.ModelConverter;
import com.citynow.accessmanagement.repositories.StudentRepository;
import com.citynow.accessmanagement.template.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class StudentUpdateImpl extends BaseService<StudentUpdate.Input,StudentUpdate.Output> implements StudentUpdate {
    private final StudentRepository studentRepository;
    @Override
    protected void preExecute(Input input) {

    }

    @Override
    protected Output doExecute(Input input) {
        Student student = new ModelConverter().map(input,Student.class);
        Student studentUpdated = studentRepository.save(student);
        return new ModelConverter().map(studentUpdated, Output.class);
    }

    @Override
    protected void postExecute(Input input) {

    }
}
