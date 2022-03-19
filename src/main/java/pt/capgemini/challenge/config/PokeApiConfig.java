package pt.capgemini.challenge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PokeApiConfig {

    @Value("${pokeapi.url}")
    private String url;

    public String getUrl() {
        return url;
    }
}
