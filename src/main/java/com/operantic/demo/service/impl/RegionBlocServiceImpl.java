package com.operantic.demo.service.impl;

import com.operantic.demo.model.RegionBloc;
import com.operantic.demo.service.CountryService;
import com.operantic.demo.service.RegionBlocService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @author Christian Amani on 09/12/2018.
 */
@Service
public class RegionBlocServiceImpl implements RegionBlocService {

    private final Logger LOG = LoggerFactory.getLogger(RegionBlocServiceImpl.class);

    private final CountryService countryService;

    @Autowired
    public RegionBlocServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public Flux<RegionBloc> find() {
        LOG.info("Debut du Process 'find'");
        return countryService.find()
                .flatMap(country -> {
                    List<RegionBloc> regionalBlocs = country.getRegionalBlocs();
                    return Flux.fromStream(regionalBlocs.stream());
                }).distinct();
    }
}
