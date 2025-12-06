package beerTycoon.observers;

import beerTycoon.BeerTycoon;
import beerTycoon.BeerTycoonGUI;
import beerTycoon.audible.AudiblePlayer;

public class AudibleGameObserver implements IObserver {
    private AudiblePlayer player;

    private static final int INITIAL_BEER_COUNT_CHECKPOINT = 100;
    private static final int BEER_COUNT_SUBSEQUENT_CHECKPOINT_MULTIPLIER = 10;
    private static final int TOTAL_BEER_COUNT_CHECKPOINTS = 5;

    private double beersPrevious = 0;
    private double currentBeers = 0;

    public AudibleGameObserver(AudiblePlayer player) {
        this.player = player;
    }

    @Override
    public void update(BeerTycoon game) {
        shoutOutMilestoneIfAchieved(game);
        extractAndUpdateGameStats(game);
    }

    private void shoutOutMilestoneIfAchieved(BeerTycoon game) {
        beersPrevious = currentBeers;
        currentBeers = game.getBeers();

        for(int checkpointCount = 0; checkpointCount < TOTAL_BEER_COUNT_CHECKPOINTS; checkpointCount++){
            int currentBeersCheckpoint = INITIAL_BEER_COUNT_CHECKPOINT * (int)Math.pow(BEER_COUNT_SUBSEQUENT_CHECKPOINT_MULTIPLIER, checkpointCount);
            if (beersPrevious < currentBeersCheckpoint && currentBeers >= currentBeersCheckpoint) {
                String formattedString = String.format("%d beers", (int) currentBeers);
                player.say(formattedString);
            }
        }
    }

    private void extractAndUpdateGameStats(BeerTycoon game) {
        int beerMakersCount = game.getBeerMakerCount();
        int upgradesCount = game.getUpgradesCount();

        BeerTycoonGUI.getInstance().updateGameStatisticsMessage(beerMakersCount, upgradesCount);
    }
}
