package BeerTycoon.BeerMakers;

public class BeerOcean implements BeerMaker{
    public static double BEER_OCEAN_COST = 1000000;
    public static double BEER_OCEAN_BEERS = 1000;
    public static String BEER_OCEAN_NAME = "Beer Ocean";

    @Override
    public double getCost() {
        return BEER_OCEAN_COST;
    }

    @Override
    public double makeBeer() {
        return BEER_OCEAN_BEERS;
    }

    public String getName() {
        return BEER_OCEAN_NAME;
    }
}
