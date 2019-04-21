package com.salil.weather.service;

import com.salil.weather.model.City;

import java.util.List;

public interface CityService {
    List<City> getCityList();
    void evictCitiesCache();
}
