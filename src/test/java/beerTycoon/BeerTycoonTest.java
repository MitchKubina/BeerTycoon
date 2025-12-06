package beerTycoon;

import beerTycoon.beerMakers.BeerMakerFactory;
import beerTycoon.beerMakers.BeerMakerType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BeerTycoonTest {

    @Test
    void testInstance() {
        BeerMakerFactory factory = new BeerMakerFactory();
        List<BeerMakerType> buttons = new ArrayList<>();
        buttons.add(BeerMakerType.MakeBeer);
        BeerTycoon game = new BeerTycoon(factory, buttons);

        assertTrue(true);
    }
}
