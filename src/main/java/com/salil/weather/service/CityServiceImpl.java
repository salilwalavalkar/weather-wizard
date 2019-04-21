package com.salil.weather.service;

import com.salil.weather.dao.CityDao;
import com.salil.weather.model.City;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CityServiceImpl implements CityService {

    @Autowired
    CacheManager cacheManager;

    @Autowired
    private CityDao cityDao;

    /**
     * The city list would not change frequently so we cache it. We need define
     * force evict mechanism using rest call based on requirements.
     */
    @Override
    @Cacheable("cities")
    public List<City> getCityList() {
        return cityDao.getCityList();
    }

    /**
     * Used to evict cache if required. This can be a scheduled call
     * or a forced REST call.
     */
    @CacheEvict(value="cities", allEntries=true)
    public void evictCitiesCache() {
        log.info("Evicting cities cache.");
    }
}
