package com.example.messagingrabbitmq.pojo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * @author Great
 */
public class Receiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        LOGGER.info("Receive<" + message + ">");
        countDownLatch.countDown();
    }

    public CountDownLatch getLatch() {
        return countDownLatch;
    }
}
