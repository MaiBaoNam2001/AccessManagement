package com.citynow.accessmanagement.service;

import com.citynow.accessmanagement.service.template.IService;
import lombok.Data;

public interface ParkingAreaFindById extends
    IService<ParkingAreaFindById.Input, ParkingAreaFindById.Output> {

  @Data
  class Input {

    private String parkingAreaId;
  }

  @Data
  class Output {

    private String id;
    private String name;
  }
}
