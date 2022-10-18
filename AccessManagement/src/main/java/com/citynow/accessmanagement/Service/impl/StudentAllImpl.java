package com.citynow.accessmanagement.Service.impl;

import com.citynow.accessmanagement.Service.StudentAll;
import com.citynow.accessmanagement.Mapper.ModelConverter;
import com.citynow.accessmanagement.Entity.Student;
import com.citynow.accessmanagement.repositories.StudentRepository;
import com.citynow.accessmanagement.template.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = {Exception.class})
@RequiredArgsConstructor
public class StudentAllImpl extends BaseService<StudentAll.Input, List<StudentAll.Output>> implements StudentAll {
    private final StudentRepository studentRepository;

    @Override
    protected void preExecute(Input input) {

    }

    @Override
    protected List<StudentAll.Output> doExecute(Input input) {
        List<Student> students = studentRepository.findAll();
        return new ModelConverter().mapAllByMappingType(students);
    }

    @Override
    protected void postExecute(Input input) {

    }


}
