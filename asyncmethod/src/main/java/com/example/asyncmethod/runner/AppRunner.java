package com.example.asyncmethod.runner;

import com.example.asyncmethod.entity.User;
import com.example.asyncmethod.service.GithubLookUpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * @author Great
 */
@Component
public class AppRunner implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppRunner.class);

    private final GithubLookUpService githubLookUpService;

    @Autowired
    public AppRunner(GithubLookUpService githubLookUpService) {
        this.githubLookUpService = githubLookUpService;
    }

    @Override
    public void run(String... args) throws Exception {
        long start = System.currentTimeMillis();
        CompletableFuture<User> page1 = githubLookUpService.findUser("PivotalSoftware");
        CompletableFuture<User> page2 = githubLookUpService.findUser("CloudFoundry");
        CompletableFuture<User> page3 = githubLookUpService.findUser("Spring-Projects");

        CompletableFuture.allOf(page1, page2, page3)
                .join();

        LOGGER.info("time: " + (System.currentTimeMillis() - start));
        LOGGER.info("-->" + page1.get());
        LOGGER.info("-->" + page2.get());
        LOGGER.info("-->" + page3.get());
    }
}
