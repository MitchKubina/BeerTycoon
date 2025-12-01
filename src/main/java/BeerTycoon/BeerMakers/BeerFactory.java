package BeerTycoon.BeerMakers;

public class BeerFactory implements BeerMaker{
    public static double BEER_FACTORY_COST = 5000;
    public static double BEER_FACTORY_BEERS = 50;
    public static String BEER_FACTORY_NAME = "Beer Factory";

    @Override
    public double getCost() {
        return BEER_FACTORY_COST;
    }

    @Override
    public double makeBeer() {
        return BEER_FACTORY_BEERS;
    }

    public String getName() {
        return BEER_FACTORY_NAME;
    }
}
