package pt.capgemini.challenge.facade;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pt.capgemini.challenge.config.OpenWeatherMapConfig;
import pt.capgemini.challenge.model.Weather;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class OpenWeatherMapFacade {

    private final WebClient webClient;
    private final OpenWeatherMapConfig openWeatherMapConfig;

    public OpenWeatherMapFacade(WebClient.Builder webClientBuilder, OpenWeatherMapConfig openWeatherMapConfig) {
        this.openWeatherMapConfig = openWeatherMapConfig;
        this.webClient = webClientBuilder.baseUrl(this.openWeatherMapConfig.getUrl()).build();
    }

    public Mono<Weather> getWeather(final String city) {

        log.info("Getting weather from OpenWeatherMap for the city: {}", city);

        final StringBuilder URI = new StringBuilder(String.format(this.openWeatherMapConfig.getCity(), city));
        URI.append("&appid=".concat(this.openWeatherMapConfig.getAppid()));
        URI.append("&units=".concat(this.openWeatherMapConfig.getUnits()));

        return this.webClient
                .get()
                .uri(URI.toString())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Weather.class);
    }

}
