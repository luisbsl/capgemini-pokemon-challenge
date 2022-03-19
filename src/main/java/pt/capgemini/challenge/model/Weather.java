package pt.capgemini.challenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Builder
public class Weather {

    @JsonProperty("weather")
    public List<WeatherNode> weathers;
    public Main main;

    @Data
    public static class WeatherNode {
        public String main;
    }

    @Data
    public static class Main {
        public Double temp;
    }

}
