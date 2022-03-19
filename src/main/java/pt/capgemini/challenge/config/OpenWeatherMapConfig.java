package pt.capgemini.challenge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OpenWeatherMapConfig {

    @Value("${openweathermap.url}")
    private String url;

    @Value("${openweathermap.city}")
    private String city;

    @Value("${openweathermap.appid}")
    private String appid;

    @Value("${openweathermap.units}")
    private String units;

    public String getUrl() {
        return url;
    }

    public String getCity() {
        return city;
    }

    public String getAppid() {
        return appid;
    }

    public String getUnits() {
        return units;
    }
}
