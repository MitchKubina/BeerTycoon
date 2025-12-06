package beerTycoon;

import beerTycoon.audible.AudiblePlayer;
import beerTycoon.beerMakers.*;

import java.util.*;
import java.util.List;
import java.util.Timer;

import beerTycoon.observers.AudibleGameObserver;
import beerTycoon.observers.IObserver;
import beerTycoon.upgrades.CostReductionUpgrade;
import beerTycoon.upgrades.EfficiencyUpgrade;
import beerTycoon.upgrades.UpgradeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeerTycoon {

    static final int UPGRADE_COST = 500;
    static final int MAKE_BEER_DEFAULT_AMOUNT = 1;
    static final int TIMER_REFRESH_PERIOD = 50;
    static final int TIMER_DELAY = 0;
    static final int STARTING_BEER_COUNT = 0;

    final static Logger logger = LoggerFactory.getLogger(BeerTycoon.class);

    List<IObserver> observers = new ArrayList<>();

    protected double beers = 0;
    protected int upgrades = 0;
    BeerMakerFactory beerMakerFactory;
    BeerTycoonGUI gui;

    List<BeerMaker> ownedBeerMakers = new ArrayList<>();
    List<BeerMaker> beerMakerShoppingCatalog = new ArrayList<>();

    //**Example of Dependency Injection With Factory**
    public BeerTycoon(BeerMakerFactory factory, List<BeerMakerType> makerButtons, List<UpgradeType> upgradeTypes) {
        this.beerMakerFactory = factory;
        generateBeerMakerButtons(makerButtons);
        setUpScreen(upgradeTypes);
        gui.updateBeerCount(STARTING_BEER_COUNT);
        setupTimer();
    }

    void setUpScreen(List<UpgradeType> upgradeTypes)  {
        gui = BeerTycoonGUI.getInstance(); //Singleton
        gui.initializeGame(this, beerMakerShoppingCatalog, upgradeTypes);
    }

    private void generateBeerMakerButtons(List<BeerMakerType> buttons) {
        for (BeerMakerType type : buttons) {
            BeerMaker beerMaker = beerMakerFactory.getBeerMaker(type);
            beerMakerShoppingCatalog.add(beerMaker);
        }
    }

    private void setupTimer() {
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                refreshCurrentGameState();
            }
        }, TIMER_DELAY, TIMER_REFRESH_PERIOD);
    }

    //Where the logic will go of calculating beers, updating values, enabling/disabling buttons
    public void refreshCurrentGameState() {
        addBeerFromBeerMakers();
        String formattedBeers = String.format("Total: %d beers", (int) beers);
        logger.info(formattedBeers);
        gui.updateBeerCount(beers);
        notifyObservers();
    }

    public void attach(IObserver observer) {
        this.observers.add(observer);
    }

    public int getBeerMakerCount() {
        return ownedBeerMakers.size();
    }

    public int getUpgradesCount() {
        return upgrades;
    }

    public void notifyObservers() {
        for (IObserver observer : observers) {
            observer.update(this);
        }
    }

    private void addBeerFromBeerMakers() {
        double calculatedBeers = 0;
        for (BeerMaker beerMaker : ownedBeerMakers) {
            calculatedBeers += beerMaker.makeBeer();
        }

        beers += calculatedBeers;
    }

    void manuallyMakeBeers() {
        this.beers += MAKE_BEER_DEFAULT_AMOUNT;
        notifyObservers();
    }

    void buyAndAddBeerMaker(BeerMakerType type) {
        BeerMaker beerMaker = beerMakerFactory.getBeerMaker(type);

        if (beerMaker.getCost() <= beers) {
            ownedBeerMakers.add(beerMaker);
            beers -= beerMaker.getCost();
            refreshCurrentGameState();
            notifyObservers();
        }
    }

    public void handleUpgradeAction(UpgradeType type) {

        if (beers >= UPGRADE_COST && !ownedBeerMakers.isEmpty()) {
            for (int i = 0; i < ownedBeerMakers.size(); i++) {
                BeerMaker maker = ownedBeerMakers.get(i);

                // Don't upgrade the manual clicker or already upgraded beer makers
                if (maker.isUpgradable()) {
                    BeerMaker upgradedMaker;
                    if (type == UpgradeType.Efficiency) {
                        upgradedMaker = new EfficiencyUpgrade(maker);
                    } else {
                        upgradedMaker = new CostReductionUpgrade(maker);
                    }

                    ownedBeerMakers.set(i, upgradedMaker);
                    beers -= UPGRADE_COST;
                    upgrades++;
                    refreshCurrentGameState();
                    notifyObservers();
                    logger.info("Upgraded " + maker.getName() + " to " + upgradedMaker.getName());
                    return;
                }
            }
        }
    }

    public void addBeerMaker(BeerMaker maker) {
        this.ownedBeerMakers.add(maker);
    }

    public double getBeers()  {
        return beers;
    }

    public static void main(String[] args) {
        BeerMakerFactory factory = new BeerMakerFactory();

        AudiblePlayer player = new AudiblePlayer();
        AudibleGameObserver observer = new AudibleGameObserver(player);

        // Beer Producers List
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
        game.attach(observer);
    }

}
