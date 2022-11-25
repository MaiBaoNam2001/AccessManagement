package com.citynow.accessmanagement.controller;

import com.citynow.accessmanagement.common.mapper.ModelConverter;
import com.citynow.accessmanagement.dto.response.ProjectFindByIdResponse;
import com.citynow.accessmanagement.service.ProjectFindById;
import com.citynow.accessmanagement.service.ProjectFindById.Input;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProjectFindByIdController {

  private final ProjectFindById projectFindById;
  private final ModelConverter modelConverter;

  @GetMapping("/projects/id/{projectId}")
  public ProjectFindByIdResponse findProjectById(
      @PathVariable(value = "projectId") String projectId) {
    ProjectFindById.Input input = new Input();
    input.setProjectId(projectId);
    return modelConverter.map(projectFindById.execute(input), ProjectFindByIdResponse.class);
  }
}
