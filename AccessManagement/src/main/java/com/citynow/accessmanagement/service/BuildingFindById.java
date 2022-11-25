package com.citynow.accessmanagement.service;

import com.citynow.accessmanagement.service.template.IService;
import lombok.Data;

public interface BuildingFindById extends
    IService<BuildingFindById.Input, BuildingFindById.Output> {

  @Data
  class Input {

    private String buildingId;
  }

  @Data
  class Output {

    private String id;
    private String name;
  }
}
