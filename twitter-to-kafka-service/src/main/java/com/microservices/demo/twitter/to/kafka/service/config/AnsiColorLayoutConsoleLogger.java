package com.microservices.demo.twitter.to.kafka.service.config;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class AnsiColorLayoutConsoleLogger extends PatternLayout {

    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";

    @Override
    public String doLayout(ILoggingEvent event) {
        return ANSI_GREEN + super.doLayout(event) + ANSI_RESET;
    }
}