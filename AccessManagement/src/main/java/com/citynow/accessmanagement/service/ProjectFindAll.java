package com.citynow.accessmanagement.service;

import com.citynow.accessmanagement.service.template.IService;
import java.util.List;
import lombok.Data;

public interface ProjectFindAll extends IService<ProjectFindAll.Input, ProjectFindAll.Output> {

  class Input {

  }

  @Data
  class Output {

    private List<Project> projects;

    @Data
    public static class Project {

      private String id;
      private String name;
    }
  }
}
