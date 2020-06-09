package com.ineor.countrypopulation.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ineor.countrypopulation.domain.Item;
import com.ineor.countrypopulation.domain.Page;
import org.json.JSONArray;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class WorldBankAPIService {
    private static final String WORLD_BANK_API_MAIN_URL =
            "http://api.worldbank.org/v2/country/all/indicator/SP.POP.TOTL?format=json";

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    public WorldBankAPIService(RestTemplateBuilder builder, ObjectMapper mapper) {
        this.restTemplate = builder.build();
        this.mapper = mapper;
    }

    public List<Item> getCountries(int year, Page page) {
        List<Item> items = new ArrayList<>();
        while (page.getCurrentPage() <= page.getTotalPages()) {
            page = callWorldBankAPI(year, page, items);
            int nextPage = page.getCurrentPage() + 1;
            page.setCurrentPage(nextPage);
        }
        return items;
    }

    private Page callWorldBankAPI(int year, Page page, List<Item> items) {
        String url = String.format("%s&date=%d&per_page=%d&page=%d", WORLD_BANK_API_MAIN_URL, year, page.getItemsPerPage(), page.getCurrentPage());
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        JSONArray array = new JSONArray(responseEntity.getBody());
        try {
            page = mapper.readValue(array.getJSONObject(0).toString(), Page.class);
            Item[] itemsArray = mapper.readValue(array.getJSONArray(1).toString(), Item[].class);
            Collections.addAll(items, itemsArray);
            return page;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
