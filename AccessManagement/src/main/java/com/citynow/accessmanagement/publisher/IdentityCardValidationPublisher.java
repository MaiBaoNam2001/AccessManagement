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

  private static final String IDENTITY_CARD_VALIDATION_EXCHANGE = "identity-card-validation.exchange";
  private static final String IDENTITY_CARD_VALIDATION_ROUTING_KEY = "identity-card-validation.routing-key";
  private final RabbitTemplate rabbitTemplate;

  public IdentityCardValidation.Output sendRequest(IdentityCardValidation.Input request) {
    return rabbitTemplate.convertSendAndReceiveAsType(IDENTITY_CARD_VALIDATION_EXCHANGE,
        IDENTITY_CARD_VALIDATION_ROUTING_KEY, request,
        ParameterizedTypeReference.forType(IdentityCardValidation.Output.class));
  }
}
