package com.example.messagingrabbitmq.runner;

import com.example.messagingrabbitmq.MessagingRabbitmqApplication;
import com.example.messagingrabbitmq.pojo.Receiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Great
 */
@Component
public class Runner implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Runner.class);

    private final Receiver receiver;

    private final RabbitTemplate rabbitTemplate;

    public Runner(Receiver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Sending message...");
        rabbitTemplate.convertAndSend(MessagingRabbitmqApplication.TOPIC_EXCHANGE_NAME,
                "foo.bar.baz", "Hello from rabbitmq!");
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }
}
