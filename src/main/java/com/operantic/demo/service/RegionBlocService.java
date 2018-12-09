package com.operantic.demo.service;

import com.operantic.demo.model.RegionBloc;
import reactor.core.publisher.Flux;

/**
 * @author Christian Amani on 04/12/2018.
 */
public interface RegionBlocService {

    Flux<RegionBloc> find();
}
