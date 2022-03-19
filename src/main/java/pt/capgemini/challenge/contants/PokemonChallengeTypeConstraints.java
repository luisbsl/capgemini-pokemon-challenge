package pt.capgemini.challenge.contants;

import java.util.function.Predicate;

public final class PokemonChallengeTypeConstraints {

    public static Predicate<Integer> ICE = x -> x < 5;
    public static Predicate<Integer> WATER = x -> x >= 5 && x < 10;
    public static Predicate<Integer> GRASS = x -> x >= 12 && x < 15;
    public static Predicate<Integer> GROUND = x -> x >= 15 && x < 21;
    public static Predicate<Integer> BUG = x -> x >= 23 && x < 27;
    public static Predicate<Integer> ROCK = x -> x >= 27 && x <= 33;
    public static Predicate<Integer> FIRE = x -> x > 33;

}
