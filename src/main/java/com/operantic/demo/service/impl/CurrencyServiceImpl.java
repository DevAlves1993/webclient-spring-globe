package com.operantic.demo.service.impl;

import com.operantic.demo.model.Country;
import com.operantic.demo.model.Currency;
import com.operantic.demo.model.Language;
import com.operantic.demo.service.CountryService;
import com.operantic.demo.service.CurrencyService;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.function.Function;

/**
 * @author Christian Amani on 09/12/2018.
 */
@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final Logger LOG = LoggerFactory.getLogger(CurrencyServiceImpl.class);

    private final CountryService countryService;

    @Autowired
    public CurrencyServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public Flux<Currency> find() {
        LOG.info("Debut du Process 'find'");
        return countryService.find()
                .flatMap(country -> {
                    List<Currency> currencies = country.getCurrencies();
                    return Flux.fromStream(currencies.stream());
                }).distinct();
    }

    @Override
    public Flux<Currency> findByLanguage(String language) {
        LOG.info("Debut du Process 'findByLanguage'");
        if (language != null) {
            return countryService.findByLanguage(language)
                    .flatMap(country -> {
                        List<Currency> currencies = country.getCurrencies();
                        return Flux.fromStream(currencies.stream());
                    }).distinct();
        }
        return Flux.empty();
    }

    @Override
    public Flux<Currency> findByRegion(String region) {
        LOG.info("Debut du Process 'findByRegion'");
        if (region != null) {
            return countryService.findByRegion(region)
                    .flatMap(country -> {
                        List<Currency> currencies = country.getCurrencies();
                        return Flux.fromStream(currencies.stream());
                    }).distinct();
        }
        return Flux.empty();
    }
}
