package com.operantic.demo.service;

import com.operantic.demo.model.Country;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author Christian Amani on 04/12/2018.
 */
public interface CountryService {

    Flux<Country> find();

    Flux<Country> findByName(String name);

    Flux<Country> findByCode(String code);

    Flux<Country> findByCodes(List<String> codes);

    Flux<Country> findByCurrency(String currency);

    Flux<Country> findByLanguage(String language);

    Mono<Country> findByCapital(String capital);

    Flux<Country> findByRegion(String region);
}
