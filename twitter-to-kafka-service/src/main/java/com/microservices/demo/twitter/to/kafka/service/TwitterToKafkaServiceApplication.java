package com.microservices.demo.twitter.to.kafka.service;

import com.microservices.demo.twitter.to.kafka.service.config.TwitterToKafkaServiceConfigData;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
public class TwitterToKafkaServiceApplication implements CommandLineRunner, ApplicationListener {

    private static final Logger LOG = LoggerFactory.getLogger(TwitterToKafkaServiceApplication.class);
    private final TwitterToKafkaServiceConfigData configData;

    public static void main(String[] args) {
        SpringApplication.run(TwitterToKafkaServiceApplication.class, args);
    }

    /**
     * This method is marked as a post-construction callback.
     * It is called by the Spring Framework after the bean has been
     * instantiated, dependency injected, and configured by Spring.
     * This method is where you can perform any necessary initialization.
     */
    @PostConstruct
    public void init() {
        LOG.info("initialization logic here");
    }

    /**
     * This method is from the CommandLineRunner interface.
     * Spring Boot calls it after the application context is launched.
     * It takes any command-line arguments and you can use it to
     * execute some logic after the application has started.
     *
     * @param args The command-line arguments
     */
    @Override
    public void run(String... args) {
        LOG.info("logic to be executed after application context is launched");
        LOG.info(Arrays.toString(configData.getTwitterKeywords().toArray(new String[]{})));
    }

    /**
     * This method is from the ApplicationListener interface.
     * Spring calls it when an application event that you've registered
     * a listener for is published. You can use this method to define
     * a certain action when a specific event occurs in the Spring Boot application context.
     *
     * @param event The published application event
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            LOG.info("Context refreshed: do logic here");
        } else if (event instanceof ApplicationStartedEvent) {
            LOG.info("Application started: do logic here");
        } else if (event instanceof AvailabilityChangeEvent) {
            LOG.info("Availability change: do logic here");
        } else if (event instanceof ApplicationReadyEvent) {
            LOG.info("Application ready: do logic here");
        } else if (event instanceof ContextStartedEvent) {
            LOG.info("Context started: do logic here");
        } else if (event instanceof ContextStoppedEvent) {
            LOG.info("Context stopped: do logic here");
        } else if (event instanceof ContextClosedEvent) {
            LOG.info("Context closed: do logic here");
        } else {
            LOG.info("Unknown event type");
        }
    }
}
