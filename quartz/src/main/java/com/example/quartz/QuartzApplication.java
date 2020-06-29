package com.example.quartz;

import com.example.quartz.job.SimpleJob;
import org.quartz.*;
import org.quartz.core.QuartzScheduler;
import org.quartz.impl.StdScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Great
 */
@SpringBootApplication
@EnableScheduling
public class QuartzApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(QuartzApplication.class, args);
    }

    @Autowired
    private Scheduler scheduler;

    @Override
    public void run(String... args) throws SchedulerException {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("* * * * * ?");

        JobDetail jobDetail = JobBuilder.newJob(SimpleJob.class)
                .withIdentity("cj", "123")
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("cj","123")
                .withSchedule(cronScheduleBuilder)
                .build();
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
