package com.microservices.demo.twitter.to.kafka.service.config;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class AnsiColorLayoutFileLogger extends PatternLayout {

    @Override
    public String doLayout(ILoggingEvent event) {
        return stripColorCodes(super.doLayout(event));
    }

    private String stripColorCodes(String message) {
        return message.replaceAll("\u001B\\[[;\\d]*m", "");
    }
}