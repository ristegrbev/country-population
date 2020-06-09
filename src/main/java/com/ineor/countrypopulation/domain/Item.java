package com.ineor.countrypopulation.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

public class Item implements Comparable<Item> {
    private Country country;

    @JsonProperty(value = "date")
    private String year;

    @JsonProperty(value = "value")
    private Long population;

    public Item() {
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(country, item.country) &&
                Objects.equals(year, item.year) &&
                Objects.equals(population, item.population);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, year, population);
    }

    @Override
    public int compareTo(Item o) {
        return this.population.compareTo(o.population);
    }
}
