package com.salil.weather.dao;

import com.salil.weather.model.City;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class is a repository/database simulator.
 */
@Repository
@Slf4j
public class CityDaoImpl implements CityDao {

    @Override
    public List<City> getCityList() {
        List<City> cityList = new ArrayList<>();
        cityList.add(new City(3614789, "Berlin"));
        cityList.add(new City(4954380, "Waltham"));
        cityList.add(new City(4142683, "Glasgow"));
        cityList.add(new City(1275339, "Mumbai"));
        log.info("{} cities loaded from database", cityList.size());

        /* Master list which should not be modified in higher layers */
        return Collections.unmodifiableList(cityList);
    }
}
