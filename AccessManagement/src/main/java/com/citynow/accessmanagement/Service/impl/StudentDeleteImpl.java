package com.citynow.accessmanagement.Service.impl;

import com.citynow.accessmanagement.template.BaseService;
import com.citynow.accessmanagement.Service.StudentDelete;
import com.citynow.accessmanagement.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class StudentDeleteImpl extends BaseService<Integer,Boolean> implements StudentDelete {
    private final StudentRepository studentRepository;
    @Override
    protected void preExecute(Integer input) {

    }

    @Override
    protected Boolean doExecute(Integer input) {
        try{
            studentRepository.deleteById(input);
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    protected void postExecute(Integer input) {

    }
}
