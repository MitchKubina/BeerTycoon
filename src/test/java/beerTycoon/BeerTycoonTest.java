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
        List<BeerMakerType> buttons = new ArrayList<>();
        buttons.add(BeerMakerType.MakeBeer);
        List<UpgradeType> upgrades = Arrays.asList(
                UpgradeType.Efficiency,
                UpgradeType.CostReduction
        );

        BeerTycoon game = new BeerTycoon(factory, buttons, upgrades);

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
        game.refreshScreen();
        game.refreshScreen();


        //because the game will refresh the screen after a second
        double expectedBeers = 150;

        assertEquals(game.getBeers(), expectedBeers);

    }
}
