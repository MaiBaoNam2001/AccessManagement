package com.citynow.accessmanagement.service.impl;

import com.citynow.accessmanagement.common.mapper.ModelConverter;
import com.citynow.accessmanagement.repository.ProjectRepository;
import com.citynow.accessmanagement.service.ProjectFindById;
import com.citynow.accessmanagement.service.template.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectFindByIdServiceImpl extends
    BaseService<ProjectFindById.Input, ProjectFindById.Output> implements ProjectFindById {

  private final ProjectRepository projectRepository;
  private final ModelConverter modelConverter;

  @Override
  protected void preExecute(Input input) {

  }

  @Override
  protected Output doExecute(Input input) {
    return modelConverter.map(projectRepository.findById(input.getProjectId()), Output.class);
  }

  @Override
  protected void postExecute(Input input) {

  }
}
