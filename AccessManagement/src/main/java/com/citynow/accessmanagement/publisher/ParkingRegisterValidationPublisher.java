package com.citynow.accessmanagement.publisher;

import com.citynow.accessmanagement.service.ParkingRegisterValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParkingRegisterValidationPublisher {

  private static final String PARKING_REGISTER_VALIDATION_EXCHANGE = "parking-register-validation.exchange";
  private static final String PARKING_REGISTER_VALIDATION_ROUTING_KEY = "parking-register-validation.routing-key";
  private final RabbitTemplate rabbitTemplate;

  public ParkingRegisterValidation.Output sendRequest(ParkingRegisterValidation.Input request) {
    return rabbitTemplate.convertSendAndReceiveAsType(PARKING_REGISTER_VALIDATION_EXCHANGE,
        PARKING_REGISTER_VALIDATION_ROUTING_KEY, request,
        ParameterizedTypeReference.forType(ParkingRegisterValidation.Output.class));
  }
}
