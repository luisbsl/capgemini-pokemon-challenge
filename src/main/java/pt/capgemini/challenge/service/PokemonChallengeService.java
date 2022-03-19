package pt.capgemini.challenge.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import pt.capgemini.challenge.contants.PokemonChallengeTypeConstraints;
import pt.capgemini.challenge.enums.PokemonType;
import pt.capgemini.challenge.facade.OpenWeatherMapFacade;
import pt.capgemini.challenge.facade.PokeApiFacade;
import pt.capgemini.challenge.model.PokemonChallenge;
import pt.capgemini.challenge.model.Weather;
import reactor.core.publisher.Mono;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class PokemonChallengeService {

    private final OpenWeatherMapFacade openWeatherMapFacade;
    private final PokeApiFacade pokeApiFacade;

    public Mono<PokemonChallenge> getPokemon(final String city) {
        return getWeatherByCityFromOpenWeatherMap(city);
    }

    private Mono<PokemonChallenge> getWeatherByCityFromOpenWeatherMap(final String city) {
        return openWeatherMapFacade.getWeather(city).flatMap(weather -> {
            String pokemonType = null;
            final boolean isItRaining = weather.weathers.get(0).main.equals("Rain");
            final String rainingOrNotText = isItRaining ? "It's raining" : "It's not raining";

            if (isItRaining) {
                pokemonType = "electric";
            }

            final int temperature = weather.main.getTemp().intValue();

            pokemonType = Strings.isBlank(pokemonType) ? getPokemonTypeByTemperature(temperature).getType() : pokemonType;

            return getPokemonByTypeFromPokeApi(pokemonType, weather, rainingOrNotText);
        });
    }

    private Mono<PokemonChallenge> getPokemonByTypeFromPokeApi(final String pokemonType, final Weather weather, final String rainingOrNot) {
        return pokeApiFacade.getPokemon(pokemonType).flatMap(pokemon -> {

            Random rand = new Random();
            final String randomPokemonName = pokemon.getPokemons().get(rand.nextInt(pokemon.getPokemons().size())).getPokemon().getName();

            PokemonChallenge pokemonChallenge = PokemonChallenge
                    .builder()
                    .temp(weather.main.getTemp())
                    .weather(rainingOrNot)
                    .pokemon(randomPokemonName)
                    .build();

            return Mono.just(pokemonChallenge);
        });
    }

    private PokemonType getPokemonTypeByTemperature(int temperature) {

        if (PokemonChallengeTypeConstraints.ICE.test(temperature)) {
            return PokemonType.ICE;
        } else if (PokemonChallengeTypeConstraints.WATER.test(temperature)) {
            return PokemonType.WATER;
        } else if (PokemonChallengeTypeConstraints.GRASS.test(temperature)) {
            return PokemonType.GRASS;
        } else if (PokemonChallengeTypeConstraints.GROUND.test(temperature)) {
            return PokemonType.GROUND;
        } else if (PokemonChallengeTypeConstraints.BUG.test(temperature)) {
            return PokemonType.BUG;
        } else if (PokemonChallengeTypeConstraints.ROCK.test(temperature)) {
            return PokemonType.ROCK;
        } else if (PokemonChallengeTypeConstraints.FIRE.test(temperature)) {
            return PokemonType.FIRE;
        }

        return PokemonType.NORMAL;
    }

}
