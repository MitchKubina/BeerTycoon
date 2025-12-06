package beerTycoon.upgrades;

import beerTycoon.beerMakers.BeerMaker;

public class EfficiencyUpgrade extends BeerMaker {

    BeerMaker beerMaker;
    private static final double BEER_MAKING_EFFICIENCY_MULTIPLIER = 1.2;

    public EfficiencyUpgrade(BeerMaker beerMaker) {
        this.beerMaker = beerMaker;
        this.upgradable = false;
    }

    @Override
    public double getCost() {
        return beerMaker.getCost();
    }

    @Override
    public double makeBeer() {
        return beerMaker.makeBeer() * BEER_MAKING_EFFICIENCY_MULTIPLIER;
    }

    @Override
    public String getName() {
        return "Efficient " + beerMaker.getName();
    }


}
