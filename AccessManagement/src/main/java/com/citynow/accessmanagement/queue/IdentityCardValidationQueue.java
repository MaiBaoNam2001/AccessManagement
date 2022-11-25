package com.citynow.accessmanagement.queue;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdentityCardValidationQueue {

  private static final String IDENTITY_CARD_VALIDATION_EXCHANGE = "identity-card-validation.exchange";
  private static final String IDENTITY_CARD_VALIDATION_DEAD_LETTER_EXCHANGE = "identity-card-validation.dead-letter-exchange";
  private static final String IDENTITY_CARD_VALIDATION_QUEUE = "identity-card-validation.queue";
  private static final String IDENTITY_CARD_VALIDATION_DEAD_LETTER_QUEUE = "identity-card-validation.dead-letter-queue";
  private static final String IDENTITY_CARD_VALIDATION_ROUTING_KEY = "identity-card-validation.routing-key";
  private static final String IDENTITY_CARD_VALIDATION_DEAD_LETTER_ROUTING_KEY = "identity-card-validation.dead-letter-routing-key";
  private static final String IDENTITY_CARD_VALIDATION_X_DEAD_LETTER_EXCHANGE = "identity-card-validation.x-dead-letter-exchange";
  private static final String IDENTITY_CARD_VALIDATION_X_DEAD_LETTER_ROUTING_KEY = "identity-card-validation.x-dead-letter-routing-key";

  @Bean
  public Declarables identityCardValidationDlqDeclarables() {
    return new Declarables(new DirectExchange(IDENTITY_CARD_VALIDATION_DEAD_LETTER_EXCHANGE),
        QueueBuilder.durable(IDENTITY_CARD_VALIDATION_DEAD_LETTER_QUEUE).lazy().build(),
        new Binding(IDENTITY_CARD_VALIDATION_DEAD_LETTER_QUEUE, DestinationType.QUEUE,
            IDENTITY_CARD_VALIDATION_DEAD_LETTER_EXCHANGE,
            IDENTITY_CARD_VALIDATION_DEAD_LETTER_ROUTING_KEY, null));
  }

  @Bean
  public Declarables identityCardValidationDeclarables() {
    return new Declarables(new TopicExchange(IDENTITY_CARD_VALIDATION_EXCHANGE),
        QueueBuilder.durable(IDENTITY_CARD_VALIDATION_QUEUE).lazy()
            .withArgument(IDENTITY_CARD_VALIDATION_X_DEAD_LETTER_EXCHANGE,
                IDENTITY_CARD_VALIDATION_DEAD_LETTER_EXCHANGE)
            .withArgument(IDENTITY_CARD_VALIDATION_X_DEAD_LETTER_ROUTING_KEY,
                IDENTITY_CARD_VALIDATION_DEAD_LETTER_ROUTING_KEY).build(),
        new Binding(IDENTITY_CARD_VALIDATION_QUEUE, DestinationType.QUEUE,
            IDENTITY_CARD_VALIDATION_EXCHANGE, IDENTITY_CARD_VALIDATION_ROUTING_KEY, null));
  }
}
