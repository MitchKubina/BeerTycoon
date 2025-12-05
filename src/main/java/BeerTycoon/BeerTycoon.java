package BeerTycoon;

import BeerTycoon.BeerMakers.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeerTycoon {

    final static Logger logger = LoggerFactory.getLogger(BeerTycoon.class);

    protected double beers = 0;
    BeerMakerFactory beerMakerFactory;
    BeerTycoonGUI gui;

    List<BeerMaker> beerMakers = new ArrayList<>();
    List<BeerMaker> buttonList = new ArrayList<>();

    public BeerTycoon(BeerMakerFactory factory, List<BeerMakerType> buttons) {
        this.beerMakerFactory = factory;
        generateBeerMakers(buttons);

        setupScreen();
        setupTimer();
    }

    private void generateBeerMakers(List<BeerMakerType> buttons) {
        for (BeerMakerType type : buttons) {
            BeerMaker beerMaker = beerMakerFactory.getBeerMaker(type);
            buttonList.add(beerMaker);

            //beerMakers.add(beerMaker);
        }
    }

    void setupScreen()  {
       gui = BeerTycoonGUI.getInstance();
       gui.setGame(this);
       gui.setButtons(buttonList);
       gui.showScreen();
    }

    private void setupTimer() {
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                refreshScreen();
            }
        }, 0, 1000);
    }

    //Where the logic will go of calculating beers, updating values, enabling/disabling buttons
    private void refreshScreen() {
        addBeer();

        String formattedBeers = String.format("Total: %d beers", (int) beers);
        logger.info(formattedBeers);

        gui.setBeers(beers);
    }

    private void addBeer() {
        double calculatedBeers = 0;
        for (BeerMaker beerMaker : beerMakers) {
            calculatedBeers += beerMaker.makeBeer();
        }

        beers += calculatedBeers;
    }

    void addBeerMaker(BeerMakerType type) {
        BeerMaker beerMaker = beerMakerFactory.getBeerMaker(type);

        if (beerMaker.getCost() <= beers) {
            beerMakers.add(beerMaker);
            beers -= beerMaker.getCost();
            gui.setBeers(beers);
        }
    }

    void addBeers(double beersToAdd) {
        this.beers += beersToAdd;
    }

    public static void main(String[] args) {
        BeerMakerFactory factory = new BeerMakerFactory();
        List<BeerMakerType> button_template = Arrays.asList(BeerMakerType.MakeBeer,
                BeerMakerType.BeerDude,
                BeerMakerType.LiquorStore,
                BeerMakerType.BeerSilo,
                BeerMakerType.BeerFactory,
                BeerMakerType.BeerOcean);

        BeerTycoon game = new BeerTycoon(factory, button_template);
    }

}
