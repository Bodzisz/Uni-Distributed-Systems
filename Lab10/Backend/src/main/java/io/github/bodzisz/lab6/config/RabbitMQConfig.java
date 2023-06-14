package io.github.bodzisz.lab6.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.management.MXBean;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    private static final String ALL_LOGS_QUEUE = "allLogsQueue";
    private static final String ERROR_LOGS_QUEUE = "errorLogsQueue";
    private static final String PERSON_EXCHANGE = "personLogsTopic";
    private static final String ROUTING_KEY_ALL_LOGS = "allLogs";
    private static final String ROUTING_KEY_ERROR_LOGS = "errorLogs";


    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(PERSON_EXCHANGE);
    }

    @Bean
    Declarables bindings() {
        Queue allLogsQueue = new Queue(ALL_LOGS_QUEUE);
        Queue errorLogsQueue = new Queue(ERROR_LOGS_QUEUE);

        return new Declarables(
                allLogsQueue,
                errorLogsQueue,
                BindingBuilder.bind(allLogsQueue).to(topicExchange()).with(ROUTING_KEY_ALL_LOGS),
                BindingBuilder.bind(errorLogsQueue).to(topicExchange()).with(ROUTING_KEY_ERROR_LOGS)
        );
    }


}
