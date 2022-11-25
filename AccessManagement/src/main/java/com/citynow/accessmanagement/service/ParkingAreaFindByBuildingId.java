package com.citynow.accessmanagement.service;

import com.citynow.accessmanagement.service.template.IService;
import java.util.List;
import lombok.Data;

public interface ParkingAreaFindByBuildingId extends
    IService<ParkingAreaFindByBuildingId.Input, ParkingAreaFindByBuildingId.Output> {

  @Data
  class Input {

    private String buildingId;
  }

  @Data
  class Output {

    private List<ParkingArea> parkingAreas;

    @Data
    public static class ParkingArea {

      private String id;
      private String name;
    }
  }
}
