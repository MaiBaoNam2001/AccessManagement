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
public class ParkingRegisterValidationQueue {

  @Value("${spring.rabbitmq.parking-register-validation.exchange}")
  private String parkingRegisterValidationExchange;
  @Value("${spring.rabbitmq.parking-register-validation.dead-letter-exchange}")
  private String parkingRegisterValidationDeadLetterExchange;
  @Value("${spring.rabbitmq.parking-register-validation.queue}")
  private String parkingRegisterValidationQueue;
  @Value("${spring.rabbitmq.parking-register-validation.dead-letter-queue}")
  private String parkingRegisterValidationDeadLetterQueue;
  @Value("${spring.rabbitmq.parking-register-validation.routing-key}")
  private String parkingRegisterValidationRoutingKey;
  @Value("${spring.rabbitmq.parking-register-validation.dead-letter-routing-key}")
  private String parkingRegisterValidationDeadLetterRoutingKey;
  @Value("${spring.rabbitmq.parking-register-validation.x-dead-letter-exchange}")
  private String parkingRegisterValidationXDeadLetterExchange;
  @Value("${spring.rabbitmq.parking-register-validation.x-dead-letter-routing-key}")
  private String parkingRegisterValidationXDeadLetterRoutingKey;

  @Bean
  public Declarables parkingRegisterValidationDlqDeclarables() {
    return new Declarables(new DirectExchange(parkingRegisterValidationDeadLetterExchange),
        QueueBuilder.durable(parkingRegisterValidationDeadLetterQueue).lazy().build(),
        new Binding(parkingRegisterValidationDeadLetterQueue, DestinationType.QUEUE,
            parkingRegisterValidationDeadLetterExchange,
            parkingRegisterValidationDeadLetterRoutingKey, null));
  }

  @Bean
  public Declarables parkingRegisterValidationDeclarables() {
    return new Declarables(new TopicExchange(parkingRegisterValidationExchange),
        QueueBuilder.durable(parkingRegisterValidationQueue).lazy()
            .withArgument(parkingRegisterValidationXDeadLetterExchange,
                parkingRegisterValidationDeadLetterExchange)
            .withArgument(parkingRegisterValidationXDeadLetterRoutingKey,
                parkingRegisterValidationDeadLetterRoutingKey).build(),
        new Binding(parkingRegisterValidationQueue, DestinationType.QUEUE,
            parkingRegisterValidationExchange, parkingRegisterValidationRoutingKey, null));
  }
}
