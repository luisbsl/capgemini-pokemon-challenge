package pt.capgemini.challenge.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;
import pt.capgemini.challenge.CapgeminiPokemonChallengeApplication;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CapgeminiPokemonChallengeApplication.class)
public class PokemonChallengeControllerIntegrationTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    void whenEndpointGetPokemonWithValidCity_thenReturnStatusOk() {
        webClient
                .get()
                .uri("/pokemons?city=cancun")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .consumeWith(response ->
                        Assertions.assertThat(response.getResponseBody()).isNotNull());
    }

    @Test
    void whenEndpointGetPokemonWithInvalidCity_thenReturnStatusNotFound() {
        webClient
                .get()
                .uri("/pokemons?city=empty")
                .exchange()
                .expectStatus()
                .isNotFound();
    }

}