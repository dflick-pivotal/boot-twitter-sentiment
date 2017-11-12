package io.pivotal.twitter.visualizer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.stereotype.Component;

@Component
public class SentimentService {

    @Autowired
    CounterService counterService;

    public void doStats() {
        counterService.increment("counter.services.sentiment.received.count");
    }
}