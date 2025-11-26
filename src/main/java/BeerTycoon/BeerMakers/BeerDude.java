package BeerTycoon.BeerMakers;

public class BeerDude implements BeerMaker{
    private static double BEER_DUDE_BEERS = 1;
    private double cost = 10;
    public static String BEER_DUDE_NAME = "Beer Dude";

    @Override
    public double makeBeer() {
        return BEER_DUDE_BEERS;
    }

    public double getCost() {
        return cost;
    }

    public String getName() {
        return BEER_DUDE_NAME;
    }
}
