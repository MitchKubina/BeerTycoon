package BeerTycoon.BeerMakers;

public class BeerSilo implements BeerMaker{
    public static double BEER_SILO_COST = 1000;
    public static double BEER_SILO_BEERS = 10;
    public static String BEER_SILO_NAME = "Beer Silo";

    @Override
    public double getCost() {
        return BEER_SILO_COST;
    }

    @Override
    public double makeBeer() {
        return BEER_SILO_BEERS;
    }

    public String getName() {
        return BEER_SILO_NAME;
    }
}
