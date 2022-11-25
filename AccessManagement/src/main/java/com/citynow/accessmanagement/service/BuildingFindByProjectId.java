package com.citynow.accessmanagement.service;

import com.citynow.accessmanagement.service.template.IService;
import java.util.List;
import lombok.Data;

public interface BuildingFindByProjectId extends
    IService<BuildingFindByProjectId.Input, BuildingFindByProjectId.Output> {

  @Data
  class Input {

    private String projectId;
  }

  @Data
  class Output {

    private List<Building> buildings;

    @Data
    public static class Building {

      private String id;
      private String name;
    }
  }
}
