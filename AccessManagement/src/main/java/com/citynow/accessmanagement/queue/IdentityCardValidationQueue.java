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

  @Value("${spring.rabbitmq.identity-card-validation.exchange}")
  private String identityCardValidationExchange;
  @Value("${spring.rabbitmq.identity-card-validation.dead-letter-exchange}")
  private String identityCardValidationDeadLetterExchange;
  @Value("${spring.rabbitmq.identity-card-validation.queue}")
  private String identityCardValidationQueue;
  @Value("${spring.rabbitmq.identity-card-validation.dead-letter-queue}")
  private String identityCardValidationDeadLetterQueue;
  @Value("${spring.rabbitmq.identity-card-validation.routing-key}")
  private String identityCardValidationRoutingKey;
  @Value("${spring.rabbitmq.identity-card-validation.dead-letter-routing-key}")
  private String identityCardValidationDeadLetterRoutingKey;
  @Value("${spring.rabbitmq.identity-card-validation.x-dead-letter-exchange}")
  private String identityCardValidationXDeadLetterExchange;
  @Value("${spring.rabbitmq.identity-card-validation.x-dead-letter-routing-key}")
  private String identityCardValidationXDeadLetterRoutingKey;

  @Bean
  public Declarables identityCardValidationDlqDeclarables() {
    return new Declarables(new DirectExchange(identityCardValidationDeadLetterExchange),
        QueueBuilder.durable(identityCardValidationDeadLetterQueue).lazy().build(),
        new Binding(identityCardValidationDeadLetterQueue, DestinationType.QUEUE,
            identityCardValidationDeadLetterExchange, identityCardValidationDeadLetterRoutingKey,
            null));
  }

  @Bean
  public Declarables identityCardValidationDeclarables() {
    return new Declarables(new TopicExchange(identityCardValidationExchange),
        QueueBuilder.durable(identityCardValidationQueue).lazy()
            .withArgument(identityCardValidationXDeadLetterExchange,
                identityCardValidationDeadLetterExchange)
            .withArgument(identityCardValidationXDeadLetterRoutingKey,
                identityCardValidationDeadLetterRoutingKey).build(),
        new Binding(identityCardValidationQueue, DestinationType.QUEUE,
            identityCardValidationExchange, identityCardValidationRoutingKey, null));
  }
}
