package com.citynow.accessmanagement.service;

import com.citynow.accessmanagement.service.template.IService;
import java.util.List;
import lombok.Data;

public interface ParkingTypeFindAll extends
    IService<ParkingTypeFindAll.Input, ParkingTypeFindAll.Output> {

  class Input {

  }

  @Data
  class Output {

    private List<ParkingType> parkingTypes;

    @Data
    public static class ParkingType {

      private String id;
      private String name;
    }
  }
}
