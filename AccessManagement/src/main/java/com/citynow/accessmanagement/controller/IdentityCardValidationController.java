package com.citynow.accessmanagement.controller;

import com.citynow.accessmanagement.common.mapper.ModelConverter;
import com.citynow.accessmanagement.dto.request.IdentityCardValidationRequest;
import com.citynow.accessmanagement.dto.response.IdentityCardValidationResponse;
import com.citynow.accessmanagement.publisher.IdentityCardValidationPublisher;
import com.citynow.accessmanagement.service.IdentityCardValidation;
import com.citynow.accessmanagement.service.IdentityCardValidation.Input;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class IdentityCardValidationController {

  private final IdentityCardValidationPublisher identityCardValidationPublisher;
  private final ModelConverter modelConverter;

  @PostMapping("/identity-card/validate")
  public IdentityCardValidationResponse validateIdentityCard(
      @RequestBody IdentityCardValidationRequest request) {
    IdentityCardValidation.Input input = modelConverter.map(request, Input.class);
    return modelConverter.map(identityCardValidationPublisher.sendRequest(input),
        IdentityCardValidationResponse.class);
  }
}
