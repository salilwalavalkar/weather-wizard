package com.salil.weather.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Weather {

    private String city;
    private String temperature;
    private String description;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("City: " + city + ";");
        builder.append("Temperature: " + temperature + ";");
        builder.append("Description: " + description + ";");
        return builder.toString();
    }
}
