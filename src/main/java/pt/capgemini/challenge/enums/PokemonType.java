package pt.capgemini.challenge.enums;

public enum PokemonType {
    ICE("ice"), WATER("water"),
    GRASS("grass"), GROUND("ground"),
    BUG("rock"), ROCK("rock"),
    FIRE("fire"), NORMAL("normal");

    private String type;

    PokemonType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}