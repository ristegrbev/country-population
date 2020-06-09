package com.ineor.countrypopulation.web.rest;

import com.ineor.countrypopulation.domain.Item;
import com.ineor.countrypopulation.services.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/countries")
public class CountryResource {

    private final CountryService service;

    public CountryResource(CountryService service) {
        this.service = service;
    }

    @GetMapping(value = "/highest")
    public ResponseEntity<List<Item>> getHighestCountries(@RequestParam(defaultValue = "2018", required = false) Integer year,
                                                          @RequestParam(defaultValue = "50", required = false) Integer countriesPerPage) {
        return ResponseEntity.ok(service.getTopThreeCountriesWithHighestPopulation(year, countriesPerPage));
    }

    @GetMapping(value = "/lowest")
    public ResponseEntity<List<Item>> getLowestCountries(@RequestParam(defaultValue = "2018", required = false) Integer year,
                                                         @RequestParam(defaultValue = "50", required = false) Integer countriesPerPage) {
        return ResponseEntity.ok(service.getTopThreeCountriesWithLowestPopulation(year, countriesPerPage));
    }
}
