package io.github.bodzisz.errorlogservice;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {
    private static final String ERROR_LOGS_QUEUE = "errorLogsQueue";
    private static final String PERSON_EXCHANGE = "personLogsTopic";
    private static final String ROUTING_KEY_ERROR_LOGS = "errorLogs";


    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(PERSON_EXCHANGE);
    }

    @Bean
    Declarables bindings() {
        Queue errorLogsQueue = new Queue(ERROR_LOGS_QUEUE);

        return new Declarables(
                errorLogsQueue,
                BindingBuilder.bind(errorLogsQueue).to(topicExchange()).with(ROUTING_KEY_ERROR_LOGS)
        );
    }

    @RabbitListener(queues = ERROR_LOGS_QUEUE)
    public void listen(String in) {
        System.out.println(in);
    }

}
