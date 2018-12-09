package com.operantic.demo.controller;

import com.operantic.demo.model.Country;
import com.operantic.demo.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

/**
 * @author Christian Amani on 09/12/2018.
 */
@Controller
public class MainController {

    private final Logger LOG = LoggerFactory.getLogger(MainController.class);


    @GetMapping(value = {"/", "index"})
    public String homePage() {
        LOG.info("Debut du Process 'homePage'");
        return "dashboard/home";
    }
}
