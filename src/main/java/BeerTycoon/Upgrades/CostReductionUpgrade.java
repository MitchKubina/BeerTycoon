package BeerTycoon.Upgrades;

import BeerTycoon.BeerMakers.BeerMaker;

public class CostReductionUpgrade extends BeerMaker {

    BeerMaker beerMaker;
    private static final double COST_REDUCTION_MULTIPLIER = 0.8;

    public CostReductionUpgrade(BeerMaker beerMaker) {
        this.beerMaker = beerMaker;
    }
    @Override
    public double getCost() {
        return beerMaker.getCost() * COST_REDUCTION_MULTIPLIER;
    }

    @Override
    public double makeBeer() {
        return beerMaker.makeBeer();
    }

    @Override
    public String getName() {
        return "Enhanced " + beerMaker.getName();
    }
}
