package beerTycoon.beerMakers;

public class BeerSilo extends BeerMaker {
    public static double BEER_SILO_COST = 1000;
    public static double BEER_SILO_BEERS = 5;
    public static String BEER_SILO_NAME = "Beer Silo";

    public BeerSilo() {
        super(BEER_SILO_NAME, BEER_SILO_COST, BEER_SILO_BEERS);
    }
}
