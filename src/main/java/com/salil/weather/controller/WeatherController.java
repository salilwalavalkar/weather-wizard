package com.salil.weather.controller;

import com.salil.weather.model.SearchCriteria;
import com.salil.weather.model.Weather;
import com.salil.weather.service.CityService;
import com.salil.weather.service.WeatherService;
import com.salil.weather.util.WeatherUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@Slf4j
public class WeatherController {

	@Autowired
	private CityService cityService;

    @Autowired
    private WeatherService weatherService;

	@GetMapping("/")
	public String weather(Map<String, Object> model) throws IOException {
        model.put("defaultWeatherList", weatherService.getWeatherForDefaultCities());
		return "weather";
	}

    @RequestMapping(value = "searchCity", method = RequestMethod.GET)
    @ResponseBody
    public List<String> searchCity(HttpServletRequest request) {
        // TODO: Not implemented yet. Ajax autocomplete call to search city.
        return Collections.emptyList();
    }

    @PostMapping("/weather/search")
    public ResponseEntity<Weather> searchWeatherForCity(@Valid @RequestBody SearchCriteria search) throws IOException {
	    if(WeatherUtil.isEmpty(search.getCityName())) {
	        return null;
        }
        Optional<Weather> weather =  weatherService.getWeatherByCityName(search.getCityName());
        return ResponseEntity.ok(weather.isPresent() ? weather.get() : null);

    }
}
