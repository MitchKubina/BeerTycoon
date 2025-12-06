package beerTycoon.beerMakers;

public class LiquorStore extends BeerMaker{
    private static double LIQUOR_STORE_COST = 100;
    private static double LIQUOR_STORE_BEERS = 0.8;
    public static String LIQUOR_STORE_NAME = "Liquor Store";

    public LiquorStore() {
        super(LIQUOR_STORE_NAME, LIQUOR_STORE_COST, LIQUOR_STORE_BEERS);
    }
}
