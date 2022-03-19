package pt.capgemini.challenge.facade;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pt.capgemini.challenge.config.PokeApiConfig;
import pt.capgemini.challenge.model.Pokemon;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class PokeApiFacade {

    private final WebClient webClient;
    private final PokeApiConfig pokeApiConfig;

    public PokeApiFacade(WebClient.Builder webClientBuilder, PokeApiConfig pokeApiConfig) {
        this.pokeApiConfig = pokeApiConfig;
        this.webClient = webClientBuilder.baseUrl(pokeApiConfig.getUrl()).build();
    }

    public Mono<Pokemon> getPokemon(final String type) {

        log.info("Getting pokemon from PokeApiFacade for the type: {}", type);

        return this.webClient.get()
                .uri(type)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Pokemon.class);
    }

}
