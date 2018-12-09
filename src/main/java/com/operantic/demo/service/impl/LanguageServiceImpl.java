package com.operantic.demo.service.impl;

import com.operantic.demo.model.Country;
import com.operantic.demo.model.Language;
import com.operantic.demo.service.CountryService;
import com.operantic.demo.service.LanguageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @author Christian Amani on 09/12/2018.
 */
@Service
public class LanguageServiceImpl implements LanguageService {

    private final Logger LOG = LoggerFactory.getLogger(LanguageServiceImpl.class);

    private final WebClient webClient;
    private final CountryService countryService;

    @Autowired
    public LanguageServiceImpl(WebClient webClient, CountryService countryService) {
        this.webClient = webClient;
        this.countryService = countryService;
    }

    @Override
    public Flux<Language> find() {
        LOG.info("Debut du Process 'find'");
        return countryService.find()
                .flatMap(country -> {
                    List<Language> currencies = country.getLanguages();
                    return Flux.fromStream(currencies.stream());
                }).distinct();
    }

    @Override
    public Flux<Language> findByName(String name) {
        LOG.info("Debut du Process ''");
        if (name != null) {
            return webClient.get()
                    .uri("lang/{name}", name)
                    .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON_UTF8)
                    .retrieve()
                    .bodyToFlux(Country.class)
                    .flatMap(country -> {
                        List<Language> currencies = country.getLanguages();
                        return Flux.fromStream(currencies.stream());
                    }).distinct();
        }
        return Flux.empty();
    }

    @Override
    public Flux<Language> findByRegion(String region) {
        LOG.info("Debut du Process 'findByRegion'");
        if (region != null) {
            return countryService.findByRegion(region)
                    .flatMap(country -> {
                        List<Language> currencies = country.getLanguages();
                        return Flux.fromStream(currencies.stream());
                    }).distinct();
        }
        return Flux.empty();
    }

    @Override
    public Flux<Language> findByCurrency(String currency) {
        LOG.info("Debut du Process 'findByRegion'");
        if (currency != null) {
            return countryService.findByCurrency(currency)
                    .flatMap(country -> {
                        List<Language> currencies = country.getLanguages();
                        return Flux.fromStream(currencies.stream());
                    }).distinct();
        }
        return Flux.empty();
    }
}
