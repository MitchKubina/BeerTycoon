package BeerTycoon.BeerMakers;

public class EnhancedBeerMaker implements BeerMaker{

    BeerMaker beerMaker;
    private static double ENHANCED_BEER_MULTIPLIER = 1.2;

    public EnhancedBeerMaker(BeerMaker beerMaker) {
        this.beerMaker = beerMaker;
    }
    @Override
    public double getCost() {
        return beerMaker.getCost();
    }

    @Override
    public double makeBeer() {
        return beerMaker.makeBeer() * ENHANCED_BEER_MULTIPLIER;
    }

    @Override
    public String getName() {
        return "Enhanced " + beerMaker.getName();
    }
}
