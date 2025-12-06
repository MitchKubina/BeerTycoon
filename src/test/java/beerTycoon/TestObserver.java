package beerTycoon;

import beerTycoon.beerMakers.BeerMaker;
import beerTycoon.observers.IObserver;

import java.util.List;

public class TestObserver implements IObserver {
    List<BeerMaker> purchasedBeerMakers;

    @Override
    public void update(BeerTycoon game) {
        purchasedBeerMakers = game.ownedBeerMakers;
    }

    public List<BeerMaker> getPurchasedBeerMakers() {
        return purchasedBeerMakers;
    }
}