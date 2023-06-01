package com.microservices.demo.twitter.to.kafka.service.runner.impl;

import com.microservices.demo.twitter.to.kafka.service.config.TwitterToKafkaServiceConfigData;
import com.microservices.demo.twitter.to.kafka.service.listener.TwitterKafkaStatusListener;
import com.microservices.demo.twitter.to.kafka.service.runner.StreamRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import javax.annotation.PreDestroy;

@Component
public class TwitterKafkaStreamRunner implements StreamRunner {

    private static final Logger LOG = LoggerFactory.getLogger(TwitterKafkaStreamRunner.class);

    /**
     * Field injection using Spring's @Autowired annotation.<br><br>
     * Pros:<br>
     * - Less boilerplate code: no need for a constructor.<br>
     * - Simplifies configuration: no need to define beans in a configuration file.<br><br>
     * Cons:<br>
     * - Tight coupling: class becomes tightly coupled with the Spring framework.<br>
     * - Not recommended: can lead to hidden dependencies, harder to debug problems.<br>
     * - Not suitable for mandatory dependencies: Spring does not guarantee that the dependency will be injected before the class is used.
     */
    @Autowired
    private TwitterToKafkaServiceConfigData twitterToKafkaServiceConfigData;
    @Autowired
    private TwitterKafkaStatusListener twitterKafkaStatusListener;

    private TwitterStream twitterStream;

    @Override
    public void start() {
        twitterStream = new TwitterStreamFactory().getInstance();
        twitterStream.addListener(twitterKafkaStatusListener);
        addFilter();
    }

    @PreDestroy
    public void shutdown() {
        if (twitterStream != null) {
            LOG.info("Closing twitter stream!");
            twitterStream.shutdown();
        }
    }

    private void addFilter() {
        String[] keywords = twitterToKafkaServiceConfigData.getTwitterKeywords().toArray(new String[0]);
        FilterQuery filterQuery = new FilterQuery(keywords);
        twitterStream.filter(filterQuery);
        LOG.info("Started filtering twitter stream for keywords");
    }
}
