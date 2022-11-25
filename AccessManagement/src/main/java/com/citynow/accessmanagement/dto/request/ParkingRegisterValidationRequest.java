package com.citynow.accessmanagement.dto.request;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;

@Data
public class ParkingRegisterValidationRequest implements Serializable {

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
