package com.operantic.demo.service;

import com.operantic.demo.model.Language;
import reactor.core.publisher.Flux;

/**
 * @author Christian Amani on 04/12/2018.
 */
public interface LanguageService {

    Flux<Language> find();

    Flux<Language> findByName(String name);

    Flux<Language> findByRegion(String region);

    Flux<Language> findByCurrency(String currency);
}
