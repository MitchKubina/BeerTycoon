package BeerTycoon.BeerMakers;

public class LiquorStore implements BeerMaker{
    private static double LIQUOR_STORE_COST = 100;
    private static double LIQUOR_STORE_BEERS = 5;
    public static String LIQUOR_STORE_NAME = "Liquor Store";

    @Override
    public double getCost() {
        return LIQUOR_STORE_COST;
    }

    @Override
    public double makeBeer() {
        return LIQUOR_STORE_BEERS;
    }

    public String getName() {
        return LIQUOR_STORE_NAME;
    }
}
