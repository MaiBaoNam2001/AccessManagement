package com.citynow.accessmanagement.service;

import com.citynow.accessmanagement.service.template.IService;
import lombok.Data;

public interface ProjectFindById extends IService<ProjectFindById.Input, ProjectFindById.Output> {

  @Data
  class Input {

    private String projectId;
  }

  @Data
  class Output {

    private String id;
    private String name;
  }
}
