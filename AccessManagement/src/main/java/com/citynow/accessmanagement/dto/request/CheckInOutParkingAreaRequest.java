package com.citynow.accessmanagement.dto.request;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CheckInOutParkingAreaRequest implements Serializable {

  private String projectId;
  private String buildingId;
  private String parkingAreaId;
  private String identityCard;
  private String licensePlate;
}
