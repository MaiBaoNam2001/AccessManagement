package com.citynow.accessmanagement.service;

import com.citynow.accessmanagement.service.template.IService;
import lombok.Data;

public interface IdentityCardValidation extends
    IService<IdentityCardValidation.Input, IdentityCardValidation.Output> {

  @Data
  class Input {

    private String projectId;
    private String buildingId;
    private String parkingAreaId;
    private String identityCard;
  }

  @Data
  class Output {

    private int statusCode;
    private Object result;
  }
}
