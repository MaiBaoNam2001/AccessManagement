package com.citynow.accessmanagement.dto.response;

import java.io.Serializable;
import lombok.Data;

@Data
public class ParkingAreaFindBuildingIdResponse implements Serializable {

  private String id;
  private String name;
}
