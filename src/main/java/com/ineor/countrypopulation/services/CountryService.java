package com.ineor.countrypopulation.services;

import com.ineor.countrypopulation.domain.Item;
import com.ineor.countrypopulation.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {
    private static final Integer TOTAL_NUMBER_OF_REGIONS = 47;

    private static final Integer NUMBER_OF_COUNTRIES = 3;

    private final WorldBankAPIService service;

    public CountryService(WorldBankAPIService service) {
        this.service = service;
    }

    public List<Item> getTopThreeCountriesWithHighestPopulation(Integer year, Integer countriesPerPage) {
        return service.getCountries(year, new Page(countriesPerPage))
                .stream()
                .skip(TOTAL_NUMBER_OF_REGIONS) // Skip the first 47 elements, because they are not countries but regions
                .filter(it -> it != null && it.getPopulation() != null) // Filter items that has null as value
                .sorted(Comparator.comparing(Item::getPopulation).reversed())
                .limit(NUMBER_OF_COUNTRIES)
                .collect(Collectors.toList());
    }

    public List<Item> getTopThreeCountriesWithLowestPopulation(Integer year, Integer countriesPerPage) {
        return service.getCountries(year, new Page(countriesPerPage))
                .stream()
                .skip(TOTAL_NUMBER_OF_REGIONS) // Skip the first 47 elements, because they are not countries but regions
                .filter(it -> it != null && it.getPopulation() != null) // Filter items that has null as value
                .sorted(Comparator.comparing(Item::getPopulation))
                .limit(NUMBER_OF_COUNTRIES)
                .collect(Collectors.toList());
    }
}
