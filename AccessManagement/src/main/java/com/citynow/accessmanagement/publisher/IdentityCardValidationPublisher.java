package com.citynow.accessmanagement.publisher;

import com.citynow.accessmanagement.service.IdentityCardValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IdentityCardValidationPublisher {

  @Value("${spring.rabbitmq.identity-card-validation.exchange}")
  private String identityCardValidationExchange;
  @Value("${spring.rabbitmq.identity-card-validation.routing-key}")
  private String identityCardValidationRoutingKey;
  private final RabbitTemplate rabbitTemplate;

  public IdentityCardValidation.Output sendRequest(IdentityCardValidation.Input request) {
    return rabbitTemplate.convertSendAndReceiveAsType(identityCardValidationExchange,
        identityCardValidationRoutingKey, request,
        ParameterizedTypeReference.forType(IdentityCardValidation.Output.class));
  }
}
