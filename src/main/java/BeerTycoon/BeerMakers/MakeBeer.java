package BeerTycoon.BeerMakers;

public class MakeBeer extends BeerMaker {
    public static double MAKE_BEER_BEERS = 1;
    private double cost = 0;
    private String name = "Make Beer";

    public double makeBeer() {
        return MAKE_BEER_BEERS;
    }

    public double getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }
}
