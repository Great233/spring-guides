package com.example.quartz.job;

import com.example.quartz.service.MyService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * @author Great
 */
@Component
public class SimpleJob extends QuartzJobBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleJob.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext)
            throws JobExecutionException {
        LOGGER.info("SampleJob.executeInternal");
    }
}
