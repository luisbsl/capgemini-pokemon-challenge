package pt.capgemini.challenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Pokemon {

    @JsonProperty("pokemon")
    public List<PokemonNode> pokemons;

    @Data
    public static class PokemonNode {
        @JsonProperty("pokemon")
        public PokemonSubNode pokemon;
    }

    @Data
    public static class PokemonSubNode {
        public String name;
    }

}
