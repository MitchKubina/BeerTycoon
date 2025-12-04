package BeerTycoon;

import BeerTycoon.BeerMakers.BeerMakerFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BeerTycoonTest {

    @Test
    void testInstance() {
        BeerMakerFactory factory = new BeerMakerFactory();
        List<String> buttons = new ArrayList<>();
        buttons.add("Make Beer");
        BeerTycoon game = new BeerTycoon(factory, buttons);

        assertTrue(true);
    }
}
