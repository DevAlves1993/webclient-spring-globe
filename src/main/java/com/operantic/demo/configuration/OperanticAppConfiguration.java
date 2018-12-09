package com.operantic.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * @author Christian Amani on 04/12/2018.
 */
@Configuration
public class OperanticAppConfiguration {

    @Bean
    WebClient webClient() {
        return WebClient.builder()
                .baseUrl("https://restcountries.eu/rest/v2/")
                .build();
    }
}
