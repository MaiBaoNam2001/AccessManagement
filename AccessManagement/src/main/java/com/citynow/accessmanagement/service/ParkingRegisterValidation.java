package com.citynow.accessmanagement.service;

import com.citynow.accessmanagement.service.template.IService;
import java.time.LocalDate;
import lombok.Data;

public interface ParkingRegisterValidation extends
    IService<ParkingRegisterValidation.Input, ParkingRegisterValidation.Output> {

  @Data
  class Input {

    private String projectId;
    private String buildingId;
    private String parkingAreaId;
    private String identityCard;
    private String licensePlate;
    private String brandName;
    private String color;
    private String vehicleType;
    private LocalDate registerDate;
    private int parkingTypeId;
  }

  @Data
  class Output {

    private int statusCode;
    private Object result;
  }
}
