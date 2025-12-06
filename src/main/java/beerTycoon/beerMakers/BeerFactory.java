package beerTycoon.beerMakers;

public class BeerFactory extends BeerMaker {
    public static double BEER_FACTORY_COST = 5000;
    public static double BEER_FACTORY_BEERS = 50;
    public static String BEER_FACTORY_NAME = "Beer Factory";

    public BeerFactory() {
        super(BEER_FACTORY_NAME, BEER_FACTORY_COST, BEER_FACTORY_BEERS);
    }
}
