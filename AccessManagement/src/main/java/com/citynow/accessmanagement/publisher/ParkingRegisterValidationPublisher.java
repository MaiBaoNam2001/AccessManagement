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

  @Value("${spring.rabbitmq.parking-register-validation.exchange}")
  private String parkingRegisterValidationExchange;
  @Value("${spring.rabbitmq.parking-register-validation.routing-key}")
  private String parkingRegisterValidationRoutingKey;
  private final RabbitTemplate rabbitTemplate;

  public ParkingRegisterValidation.Output sendRequest(ParkingRegisterValidation.Input request) {
    return rabbitTemplate.convertSendAndReceiveAsType(parkingRegisterValidationExchange,
        parkingRegisterValidationRoutingKey, request,
        ParameterizedTypeReference.forType(ParkingRegisterValidation.Output.class));
  }
}
