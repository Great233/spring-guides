package com.example.messagingredis.pojo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Great
 */
public class Receiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    private final AtomicInteger counter = new AtomicInteger();

    public void receiveMessage(String message) {
        LOGGER.info("Received{" + message + "}");
        counter.incrementAndGet();
    }

    public int getCount() {
        return counter.get();
    }
}
