package com.citynow.accessmanagement.service;

import com.citynow.accessmanagement.dto.response.CheckInOutParkingAreaResponse;
import com.citynow.accessmanagement.dto.response.CheckInOutParkingAreaResponse.Result;
import com.citynow.accessmanagement.service.template.IService;
import java.time.LocalDateTime;
import lombok.Data;

public interface CheckInOutParkingArea extends
    IService<CheckInOutParkingArea.Input, CheckInOutParkingArea.Output> {

  @Data
  class Input {

    private String projectId;
    private String buildingId;
    private String parkingAreaId;
    private String identityCard;
    private String licensePlate;
  }

  @Data
  class Output {

    private int statusCode;
    private Result result;

    @Data
    public static class Result {

      private String projectId;
      private String buildingId;
      private String parkingAreaId;
    }
  }

  String MOTORBIKE = "xe máy";
  String CHECK_IN = "CHECK IN";
  String CHECK_OUT = "CHECK OUT";
}
