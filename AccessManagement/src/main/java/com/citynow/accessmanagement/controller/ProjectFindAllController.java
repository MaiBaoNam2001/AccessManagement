package com.citynow.accessmanagement.controller;

import com.citynow.accessmanagement.common.mapper.ModelConverter;
import com.citynow.accessmanagement.dto.response.ProjectFindAllResponse;
import com.citynow.accessmanagement.service.ProjectFindAll;
import com.citynow.accessmanagement.service.ProjectFindAll.Input;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProjectFindAllController {

  private final ProjectFindAll projectFindAll;
  private final ModelConverter modelConverter;

  @GetMapping("/projects")
  public List<ProjectFindAllResponse> findAllProjects() {
    ProjectFindAll.Input input = new Input();
    return modelConverter.mapAllByIterator(projectFindAll.execute(input).getProjects(),
        ProjectFindAllResponse.class);
  }
}
