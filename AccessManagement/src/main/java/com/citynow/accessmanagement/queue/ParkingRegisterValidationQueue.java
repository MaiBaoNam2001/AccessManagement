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

  private static final String PARKING_REGISTER_VALIDATION_EXCHANGE = "parking-register-validation.exchange";
  private static final String PARKING_REGISTER_VALIDATION_DEAD_LETTER_EXCHANGE = "parking-register-validation.dead-letter-exchange";
  private static final String PARKING_REGISTER_VALIDATION_QUEUE = "parking-register-validation.queue";
  private static final String PARKING_REGISTER_VALIDATION_DEAD_LETTER_QUEUE = "parking-register-validation.dead-letter-queue";
  private static final String PARKING_REGISTER_VALIDATION_ROUTING_KEY = "parking-register-validation.routing-key";
  private static final String PARKING_REGISTER_VALIDATION_DEAD_LETTER_ROUTING_KEY = "parking-register-validation.dead-letter-routing-key";
  private static final String PARKING_REGISTER_VALIDATION_X_DEAD_LETTER_EXCHANGE = "parking-register-validation.x-dead-letter-exchange";
  private static final String PARKING_REGISTER_VALIDATION_X_DEAD_LETTER_ROUTING_KEY = "parking-register-validation.x-dead-letter-routing-key";

  @Bean
  public Declarables parkingRegisterValidationDlqDeclarables() {
    return new Declarables(new DirectExchange(PARKING_REGISTER_VALIDATION_DEAD_LETTER_EXCHANGE),
        QueueBuilder.durable(PARKING_REGISTER_VALIDATION_DEAD_LETTER_QUEUE).lazy().build(),
        new Binding(PARKING_REGISTER_VALIDATION_DEAD_LETTER_QUEUE, DestinationType.QUEUE,
            PARKING_REGISTER_VALIDATION_DEAD_LETTER_EXCHANGE,
            PARKING_REGISTER_VALIDATION_DEAD_LETTER_ROUTING_KEY, null));
  }

  @Bean
  public Declarables parkingRegisterValidationDeclarables() {
    return new Declarables(new TopicExchange(PARKING_REGISTER_VALIDATION_EXCHANGE),
        QueueBuilder.durable(PARKING_REGISTER_VALIDATION_QUEUE).lazy()
            .withArgument(PARKING_REGISTER_VALIDATION_X_DEAD_LETTER_EXCHANGE,
                PARKING_REGISTER_VALIDATION_DEAD_LETTER_EXCHANGE)
            .withArgument(PARKING_REGISTER_VALIDATION_X_DEAD_LETTER_ROUTING_KEY,
                PARKING_REGISTER_VALIDATION_DEAD_LETTER_ROUTING_KEY).build(),
        new Binding(PARKING_REGISTER_VALIDATION_QUEUE, DestinationType.QUEUE,
            PARKING_REGISTER_VALIDATION_EXCHANGE, PARKING_REGISTER_VALIDATION_ROUTING_KEY, null));
  }
}
