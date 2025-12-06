package beerTycoon.beerMakers;

public class BeerDude extends BeerMaker{
    private static double BEER_DUDE_BEERS = 0.1;
    private static double PURCHASE_COST = 10;
    public static String BEER_DUDE_NAME = "Beer Dude";

    public BeerDude() {
        super(BEER_DUDE_NAME, PURCHASE_COST, BEER_DUDE_BEERS);
    }
}
