package com.operantic.demo.controller;

import com.operantic.demo.model.Country;
import com.operantic.demo.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author Christian Amani on 09/12/2018.
 */
@RestController
public class CountryController {

    private final Logger LOG = LoggerFactory.getLogger(CountryController.class);

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("country/all")
    public Flux<Country> findCountry() {
        LOG.info("Debut du Process 'findCountry'");
        return countryService.find();
    }

    @GetMapping("country/all/region/{region}")
    public Flux<Country> findByRegion(@PathVariable String region) {
        LOG.info("Debut du Process 'findByRegion'");
        return countryService.findByRegion(region);
    }

    @GetMapping("country/all/language/{language}")
    public Flux<Country> findByLanguage(@PathVariable String language) {
        LOG.info("Debut du Process 'findByLanguage'");
        return countryService.findByLanguage(language);
    }
}
