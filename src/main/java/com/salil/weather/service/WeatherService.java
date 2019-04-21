package com.salil.weather.service;

import com.salil.weather.model.Weather;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface WeatherService {

    /**
     * We would have used Builder class as parameter if there are more then one parameters.
     */
    Optional<Weather> getWeatherByCityId(final long id) throws IOException;

    Optional<Weather> getWeatherByCityName(final String name) throws IOException;

    /**
     * Get weather for default cities
     */
    List<Weather> getWeatherForDefaultCities() throws IOException;
}
