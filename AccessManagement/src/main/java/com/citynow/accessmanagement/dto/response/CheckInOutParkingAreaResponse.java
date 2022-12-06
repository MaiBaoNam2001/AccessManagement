package com.citynow.accessmanagement.dto.response;

import java.io.Serializable;
import lombok.Data;

@Data
public class CheckInOutParkingAreaResponse implements Serializable {

  private int statusCode;
  private Result result;

  @Data
  public static class Result {

    private String projectId;
    private String buildingId;
    private String parkingAreaId;
  }
}
