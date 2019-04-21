package com.salil.weather.service;

import com.salil.weather.model.Weather;
import com.salil.weather.util.JsonWeatherParser;
import com.salil.weather.util.WeatherUtil;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

@Service
@Slf4j
public class WeatherServiceImpl implements WeatherService {

    @NonNull
    @Value("${openweather.api.key}")
    private String apiKey;

    @NonNull
    @Value("${openweather.api.url}")
    private String urlPrefix;

    @NonNull
    @Value("${default.cities}")
    private String defaultCities;

    @Override
    public Optional<Weather> getWeatherByCityId(final long id) throws IOException {
        log.info("Getting weather for cityId: {}", id);
        if(id == 0) {
            log.error("City id cannot be 0");
            return Optional.empty();
        }
        return getWeather(constructURL("id", String.valueOf(id)));
    }

    @Override
    public Optional<Weather> getWeatherByCityName(String name) throws IOException {
        log.info("Getting weather for cityName: {}", name);
        if(WeatherUtil.isEmpty(name)) {
            log.error("City name cannot be empty");
            return Optional.empty();
        }
        return getWeather(constructURL("q", name));
    }

    @Override
    public List<Weather> getWeatherForDefaultCities() throws IOException {
        List<Weather> cityWeatherList = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(defaultCities, ";");
        while (tokenizer.hasMoreTokens()) {
            cityWeatherList.add(getWeatherByCityName(tokenizer.nextToken()).get());
        }
        return cityWeatherList;
    }

    private URL constructURL(String parameterName, String parameterValue) throws MalformedURLException {
        return new URL(urlPrefix + "?APPID=" + apiKey + "&units=metric&" + parameterName + "=" + parameterValue);
    }

    private Optional<Weather> getWeather(final URL url) {
        try {
            return JsonWeatherParser.parseWeather(getWeatherFromServer(url));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    private String getWeatherFromServer(final URL url) throws IOException {

        StringBuilder result = new StringBuilder();
        URLConnection urlConnection = url.openConnection();

        if(urlConnection.getInputStream() == null) {
            throw new IOException("No data found on server");
        }

        try (BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                result.append(inputLine);
            }
        }
        return result.toString();
    }
}
