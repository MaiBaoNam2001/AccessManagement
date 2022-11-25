package com.citynow.accessmanagement.dto.response;

import java.io.Serializable;
import lombok.Data;

@Data
public class IdentityCardValidationResponse implements Serializable {

  private int statusCode;
  private Object result;
}
