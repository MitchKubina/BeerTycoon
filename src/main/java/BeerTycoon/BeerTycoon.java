package BeerTycoon;

import BeerTycoon.BeerMakers.*;

import java.util.*;
import java.util.List;
import java.util.Timer;

import BeerTycoon.Observers.GameObserver;
import BeerTycoon.Upgrades.CostReductionUpgrade;
import BeerTycoon.Upgrades.EfficiencyUpgrade;
import BeerTycoon.Upgrades.UpgradeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeerTycoon {

    static final int UPGRADE_COST = 500;
    static final int MAKE_BEER_DEFAULT_AMOUNT = 100;
    static final int TIMER_REFRESH_PERIOD = 1000;
    static final int TIMER_DELAY = 0;

    final static Logger logger = LoggerFactory.getLogger(BeerTycoon.class);

    List<GameObserver> observers = new ArrayList<>();

    protected double beers = 0;
    BeerMakerFactory beerMakerFactory;
    BeerTycoonGUI gui;

    List<BeerMaker> ownedBeerMakers = new ArrayList<>();
    List<BeerMaker> beerMakerShoppingCatalog = new ArrayList<>();

    //**Example of Dependency Injection With Factory**
    public BeerTycoon(BeerMakerFactory factory, List<BeerMakerType> makerButtons, List<UpgradeType> upgradeButtons) {
        this.beerMakerFactory = factory;
        generateBeerMakerButtons(makerButtons);
        setUpScreen(upgradeButtons);
        setupTimer();
    }

    void setUpScreen(List<UpgradeType> upgradeButtons)  {
        gui = BeerTycoonGUI.getInstance(); //Singleton
        gui.setGame(this);
        gui.setButtons(beerMakerShoppingCatalog, upgradeButtons);
        gui.showScreen();
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
                refreshScreen();
            }
        }, TIMER_DELAY, TIMER_REFRESH_PERIOD);
    }

    //Where the logic will go of calculating beers, updating values, enabling/disabling buttons
    private void refreshScreen() {
        addBeerFromBeerMakers();

        String formattedBeers = String.format("Total: %d beers", (int) beers);
        logger.info(formattedBeers);

        gui.setBeers(beers);
    }

    private void addBeerFromBeerMakers() {
        double calculatedBeers = 0;
        for (BeerMaker beerMaker : ownedBeerMakers) {
            calculatedBeers += beerMaker.makeBeer();
        }

        beers += calculatedBeers;
    }

    void manuallyMakeBeers(double beersToAdd) {
        this.beers += beersToAdd;
    }

    void addBeerMaker(BeerMakerType type) {
        BeerMaker beerMaker = beerMakerFactory.getBeerMaker(type);

        if (beerMaker.getCost() <= beers) {
            ownedBeerMakers.add(beerMaker);
            beers -= beerMaker.getCost();
            gui.setBeers(beers);
        }
    }

    public void handleMakerAction(BeerMakerType type) {
        if (type == BeerMakerType.MakeBeer) {
            manuallyMakeBeers(MAKE_BEER_DEFAULT_AMOUNT);
        } else {
            addBeerMaker(type);
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
                    gui.setBeers(beers);
                    logger.info("Upgraded " + maker.getName() + " to " + upgradedMaker.getName());
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        BeerMakerFactory factory = new BeerMakerFactory();

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
    }

}
