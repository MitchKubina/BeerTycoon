package beerTycoon.beerMakers;

public class MakeBeer extends BeerMaker {
    public static final double MAKE_BEER_BEERS = 1;
    private static final double MAKE_BEER_COST = 0;
    private static final String MAKE_BEER_NAME = "Make Beer";

    public MakeBeer() {
        super(MAKE_BEER_NAME, MAKE_BEER_COST, MAKE_BEER_BEERS);
        this.upgradable = false;
    }
}
