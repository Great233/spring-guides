package com.example.asyncmethod.service;

import com.example.asyncmethod.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

/**
 * @author Great
 */
@Service
public class GithubLookUpService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GithubLookUpService.class);

    private final RestTemplate restTemplate;

    @Autowired
    public GithubLookUpService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Async
    public CompletableFuture<User> findUser(String user) throws InterruptedException {
        long start = System.currentTimeMillis();
        LOGGER.info("Looking up " + user);
        String url = String.format("https://api.github.com/users/%s", user);
        User results = restTemplate.getForObject(url, User.class);
//        Thread.sleep(1000L);
        LOGGER.info("Looked up " + user);
        LOGGER.info("time: " + (System.currentTimeMillis() - start));
        return CompletableFuture.completedFuture(results);
    }
}
