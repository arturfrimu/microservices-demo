package com.microservices.demo.twitter.to.kafka.service;

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

@SpringBootApplication
public class TwitterToKafkaServiceApplication implements CommandLineRunner, ApplicationListener {
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
        System.out.println("initialization logic here");
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
        System.out.println("logic to be executed after application context is launched");
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
            System.out.println("Context refreshed: do logic here" + ANSI_RESET);
        } else if (event instanceof ApplicationStartedEvent) {
            System.out.println(ANSI_GREEN + "Application started: do logic here" + ANSI_RESET);
        } else if (event instanceof AvailabilityChangeEvent) {
            System.out.println(ANSI_GREEN + "Availability change: do logic here" + ANSI_RESET);
        } else if (event instanceof ApplicationReadyEvent) {
            System.out.println(ANSI_GREEN + "Application ready: do logic here" + ANSI_RESET);
        } else if (event instanceof ContextStartedEvent) {
            System.out.println(ANSI_GREEN + "Context started: do logic here" + ANSI_RESET);
        } else if (event instanceof ContextStoppedEvent) {
            System.out.println(ANSI_GREEN + "Context stopped: do logic here" + ANSI_RESET);
        } else if (event instanceof ContextClosedEvent) {
            System.out.println(ANSI_GREEN + "Context closed: do logic here" + ANSI_RESET);
        } else {
            System.out.println(ANSI_GREEN + "Unknown event type" + ANSI_RESET);
        }
    }

    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";

}
