package com.citynow.accessmanagement.dto.request;

import java.io.Serializable;
import lombok.Data;

@Data
public class IdentityCardValidationRequest implements Serializable {

  private String projectId;
  private String buildingId;
  private String parkingAreaId;
  private String identityCard;
}
