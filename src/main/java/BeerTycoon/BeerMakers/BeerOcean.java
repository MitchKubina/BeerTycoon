package BeerTycoon.BeerMakers;

public class BeerOcean extends BeerMaker{
    public static double BEER_OCEAN_COST = 1000000;
    public static double BEER_OCEAN_BEERS = 1000;
    public static String BEER_OCEAN_NAME = "Beer Ocean";

    public BeerOcean() {
        super(BEER_OCEAN_NAME, BEER_OCEAN_COST, BEER_OCEAN_BEERS);
    }
}
