package io.github.bodzisz.alllogsservice;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    private static final String ALL_LOGS_QUEUE = "allLogsQueue";
    private static final String PERSON_EXCHANGE = "personLogsTopic";
    private static final String ROUTING_KEY_ALL_LOGS = "allLogs";


    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(PERSON_EXCHANGE);
    }

    @Bean
    Declarables bindings() {
        Queue allLogsQueue = new Queue(ALL_LOGS_QUEUE);

        return new Declarables(
                allLogsQueue,
                BindingBuilder.bind(allLogsQueue).to(topicExchange()).with(ROUTING_KEY_ALL_LOGS)
        );
    }

    @RabbitListener(queues = ALL_LOGS_QUEUE)
    public void listen(String in) {
        System.out.println(in);
    }

}
