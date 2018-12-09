package com.operantic.demo.service;

import com.operantic.demo.model.Currency;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author  Christian Amani on 04/12/2018.
 */
public interface CurrencyService {

    Flux<Currency> find();

    Flux<Currency> findByLanguage(String language);

    Flux<Currency> findByRegion(String region);
}
