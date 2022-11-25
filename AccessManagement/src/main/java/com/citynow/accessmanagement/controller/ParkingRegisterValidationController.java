package com.citynow.accessmanagement.controller;

import com.citynow.accessmanagement.common.mapper.ModelConverter;
import com.citynow.accessmanagement.dto.request.ParkingRegisterValidationRequest;
import com.citynow.accessmanagement.dto.response.ParkingRegisterValidationResponse;
import com.citynow.accessmanagement.publisher.ParkingRegisterValidationPublisher;
import com.citynow.accessmanagement.service.ParkingRegisterValidation;
import com.citynow.accessmanagement.service.ParkingRegisterValidation.Input;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ParkingRegisterValidationController {

  private final ParkingRegisterValidationPublisher parkingRegisterValidationPublisher;
  private final ModelConverter modelConverter;

  @PostMapping("/parking-register/validate")
  public ParkingRegisterValidationResponse validateParkingRegister(
      @RequestBody ParkingRegisterValidationRequest request) {
    ParkingRegisterValidation.Input input = modelConverter.map(request, Input.class);
    return modelConverter.map(parkingRegisterValidationPublisher.sendRequest(input),
        ParkingRegisterValidationResponse.class);
  }
}
