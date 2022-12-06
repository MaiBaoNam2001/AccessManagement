package com.citynow.accessmanagement.dto.response;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class CheckInOutHistoryParkingAreaResponse implements Serializable {

  private String identityCard;
  private String licensePlate;
  private String vehicleType;
  private String checkType;
  private String checkTime;
}
