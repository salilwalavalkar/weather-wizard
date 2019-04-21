package com.salil.weather.util;

import com.salil.weather.model.Weather;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Iterator;
import java.util.Optional;

@Slf4j
public class JsonWeatherParser {

	private static final JSONParser parser = new JSONParser();

	public static Optional<Weather> parseWeather(final String jsonString) {

	    if(WeatherUtil.isEmpty(jsonString)) {
            log.error("Empty string cannot be parsed");
            return Optional.empty();
        }

		Weather weather = new Weather();
        Object obj = null;
        try {
            obj = parser.parse(jsonString);
        } catch (ParseException e) {
            log.error("Parsing error: ", e.getMessage());
            return Optional.empty();
        }

        JSONObject mainSection = (JSONObject) obj;

        // City Name
        weather.setCity(String.valueOf(mainSection.get("name")));

        // Temp
		JSONObject mainSectionArray = (JSONObject) mainSection.get("main");
        weather.setTemperature(getTemperature(mainSectionArray));

        // Description
		JSONArray weatherArray = (JSONArray) mainSection.get("weather");
		Iterator<JSONObject> weatherIterator = weatherArray.iterator();
		while (weatherIterator.hasNext()) {
			JSONObject arrayValue = weatherIterator.next();
			String description = (String) arrayValue.get("description");
			weather.setDescription(description);
		}
		return Optional.of(weather);
	}

	private static String getTemperature(final JSONObject jsonObject) {
		Double temp = new Double(jsonObject.get("temp").toString());
		return (temp > 0 ? "+" : "") + temp;
	}
}