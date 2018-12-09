package com.operantic.demo.service.impl;

import com.operantic.demo.model.Country;
import com.operantic.demo.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

/**
 * @author Christian Amani on 04/12/2018.
 */
@Service
public class CountryServiceImpl implements CountryService {

    private final Logger LOG = LoggerFactory.getLogger(CountryServiceImpl.class);

    private final WebClient webClient;

    @Autowired
    public CountryServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Flux<Country> find() {
        LOG.info("Execution du Process 'find'");
        return webClient.get()
                .uri("all")
                .accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_JSON_UTF8)
                .retrieve()
                .bodyToFlux(Country.class);
    }

    @Override
    public Flux<Country> findByName(String name) {
        LOG.info("Execution du Process 'find'");
        if (name != null) {
            return webClient.get()
                    .uri("name/{name}", name)
                    .accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_JSON_UTF8)
                    .retrieve()
                    .bodyToFlux(Country.class);
        }
        return Flux.empty();
    }

    @Override
    public Flux<Country> findByCode(String code) {
        LOG.info("Execution du Process 'findByCode'");
        if (code != null) {
            return webClient.get()
                    .uri("aplha/{code}", code)
                    .accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_JSON_UTF8)
                    .retrieve()
                    .bodyToFlux(Country.class);
        }
        return Flux.empty();
    }

    @Override
    public Flux<Country> findByCodes(List<String> codes) {
        LOG.info("Execution du Process 'findByCodes'");
        if (codes != null && !codes.isEmpty()) {
            StringBuffer allCode = buildParamCodes(codes);
            return webClient.get()
                    .uri("aplha?codes={allCode}", allCode)
                    .accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_JSON_UTF8)
                    .retrieve()
                    .bodyToFlux(Country.class);
        }
        return Flux.empty();
    }

    @Override
    public Flux<Country> findByCurrency(String currency) {
        LOG.info("Execution du Process 'findByCurrency'");
        if (currency != null) {
            return webClient.get()
                    .uri("currency/{currency}", currency)
                    .accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_JSON_UTF8)
                    .retrieve()
                    .bodyToFlux(Country.class);
        }
        return Flux.empty();
    }

    @Override
    public Flux<Country> findByLanguage(String language) {
        LOG.info("Execution du Process 'findByLanguage'");
        if (language != null) {
            return webClient.get()
                    .uri("lang/{language}", language)
                    .accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_JSON_UTF8)
                    .retrieve()
                    .bodyToFlux(Country.class);
        }
        return Flux.empty();
    }

    @Override
    public Mono<Country> findByCapital(String capital) {
        LOG.info("Execution du Process 'findByCapital'");
        if (capital != null) {
            return webClient.get()
                    .uri("capital/{capital}", capital)
                    .accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_JSON_UTF8)
                    .retrieve()
                    .bodyToMono(Country.class);
        }
        return Mono.empty();
    }

    @Override
    public Flux<Country> findByRegion(String region) {
        LOG.info("Execution du Process 'findByRegion'");
        if (region != null) {
            return webClient.get()
                    .uri("region/{region}", region)
                    .accept(MediaType.APPLICATION_JSON,MediaType.APPLICATION_JSON_UTF8)
                    .retrieve()
                    .bodyToFlux(Country.class);
        }
        return Flux.empty();
    }

    private StringBuffer buildParamCodes(List<String> codes) {
        StringBuffer allCode = new StringBuffer("");
        for (String code : codes) {
            allCode.append(code)
                    .append(";");
        }
        int length = allCode.length();
        return allCode.deleteCharAt(length - 1);
    }

}
