package com.epicsoftwares.microservices.currency_exchange_service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api")
//    @Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
    @CircuitBreaker(name = "sample-api", fallbackMethod = "hardcodedResponse")
    public String sampleAPI() {
        logger.info("Sample API Called Received");
        ResponseEntity<String> entity =
                new RestTemplate().getForEntity("http://localhost:8080/djoladfnhdf", String.class);
        return entity.getBody();
    }

    private String hardcodedResponse(Exception e) {
        return "fallback-response";
    }
}
