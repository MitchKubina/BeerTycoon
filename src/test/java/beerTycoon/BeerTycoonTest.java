package beerTycoon;

import beerTycoon.beerMakers.BeerMaker;
import beerTycoon.beerMakers.BeerMakerFactory;
import beerTycoon.beerMakers.BeerMakerType;
import beerTycoon.upgrades.UpgradeType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BeerTycoonTest {

    BeerMakerFactory factory = new BeerMakerFactory();

    @Test
    void testInstance() {
        List<BeerMakerType> makers = new ArrayList<>();
        makers.add(BeerMakerType.MakeBeer);
        List<UpgradeType> upgrades = Arrays.asList(
                UpgradeType.Efficiency,
                UpgradeType.CostReduction
        );

        BeerTycoon game = new BeerTycoon(factory, makers, upgrades);
        game.attach(new TestObserver());
        assertTrue(true);
    }

    @Test
    void testBeers() {
        List<BeerMakerType> makers = Arrays.asList(
                BeerMakerType.MakeBeer,
                BeerMakerType.BeerDude,
                BeerMakerType.LiquorStore,
                BeerMakerType.BeerSilo,
                BeerMakerType.BeerFactory,
                BeerMakerType.BeerOcean
        );

        // Upgrades List
        List<UpgradeType> upgrades = Arrays.asList(
                UpgradeType.Efficiency,
                UpgradeType.CostReduction
        );

        BeerTycoon game = new BeerTycoon(factory, makers, upgrades);

        BeerMaker beerFactory = factory.getBeerMaker(BeerMakerType.BeerFactory);
        game.addBeerMaker(beerFactory);
        game.refreshCurrentGameState();
        game.refreshCurrentGameState();
        game.notifyObservers();


        //because the game will refresh the screen after a second
        double expectedBeers = 30.0;

        assertEquals(game.getBeers(), expectedBeers);

    }

    @Test
    void testPurchasingBeerMaker() {
        List<BeerMakerType> makers = Arrays.asList(
                BeerMakerType.MakeBeer,
                BeerMakerType.BeerDude,
                BeerMakerType.LiquorStore,
                BeerMakerType.BeerSilo,
                BeerMakerType.BeerFactory,
                BeerMakerType.BeerOcean
        );

        // Upgrades List
        List<UpgradeType> upgrades = Arrays.asList(
                UpgradeType.Efficiency,
                UpgradeType.CostReduction
        );

        BeerTycoon game = new BeerTycoon(factory, makers, upgrades);
        TestObserver observer = new TestObserver();
        game.attach(observer);

        BeerMaker beerFactory = factory.getBeerMaker(BeerMakerType.BeerFactory);
        game.addBeerMaker(beerFactory);

        game.notifyObservers();


        assertEquals(observer.getPurchasedBeerMakers().size(), 1 );

    }
}
