package pt.capgemini.challenge.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import pt.capgemini.challenge.model.PokemonChallenge;
import pt.capgemini.challenge.service.PokemonChallengeService;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("pokemons")
@RequiredArgsConstructor
public class PokemonChallengeController {

    private final PokemonChallengeService pokemonChallengeService;

    @GetMapping
    public Mono<PokemonChallenge> getPokemon(@RequestParam String city) {
        log.info("Initializing request processing");
        return pokemonChallengeService.getPokemon(city);
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<String> handleWebClientResponseException(WebClientResponseException ex) {
        log.error("Error from WebClient - Status {}, Body {}", ex.getRawStatusCode(), ex.getResponseBodyAsString(), ex);
        return ResponseEntity.status(ex.getRawStatusCode()).body(ex.getResponseBodyAsString());
    }
}
