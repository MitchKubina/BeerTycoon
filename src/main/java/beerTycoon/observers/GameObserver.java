package beerTycoon.observers;

import beerTycoon.BeerTycoon;
import beerTycoon.audible.AudiblePlayer;

public class GameObserver implements IObserver {
    private AudiblePlayer player;

    public GameObserver(AudiblePlayer player) {
        this.player = player;
    }

    @Override
    public void update(BeerTycoon game) {
        //TODO: do audible stuff; callout beer count
        //Audible not working, can't fix dependencies for javax.speech :
        double beers = game.getBeers();
        String formattedString = String.format("%d beers", (int) beers);
        player.say(formattedString);
    }
}
