package com.citynow.accessmanagement.dto.response;

import java.io.Serializable;
import lombok.Data;

@Data
public class ParkingRegisterValidationResponse implements Serializable {

  private int statusCode;
  private Object result;
}
