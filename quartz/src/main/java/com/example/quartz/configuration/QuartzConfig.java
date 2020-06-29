package com.example.quartz.configuration;

import com.example.quartz.factory.MyJobFactory;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;

/**
 * @author Great
 */
@Configuration
public class QuartzConfig {

    DataSource dataSource;

    @Autowired
    public QuartzConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(MyJobFactory myJobFactory) {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();

        schedulerFactoryBean.setDataSource(dataSource);
        schedulerFactoryBean.setJobFactory(myJobFactory);

        return schedulerFactoryBean;
    }

    @Bean
    public Scheduler scheduler(SchedulerFactoryBean schedulerFactoryBean) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return scheduler;
    }
}
