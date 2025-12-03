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

    public BeerTycoon(BeerMakerFactory factory, List<String> buttons) {
        this.beerMakerFactory = factory;
        generateBeerMakers(buttons);

        setupScreen();
        setupTimer();
    }

    private void generateBeerMakers(List<String> buttons) {
        for (String beerMakerName : buttons) {
            BeerMaker beerMaker = beerMakerFactory.getBeerMaker(beerMakerName);
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

    void addBeerMaker(String name) {
        BeerMaker beerMaker = beerMakerFactory.getBeerMaker(name);

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
        List<String> BUTTON_TEMPLATE = Arrays.asList("Make Beer", "Beer Dude", "Liquor Store", "Beer Silo", "Beer Factory", "Beer Ocean");

        BeerTycoon game = new BeerTycoon(factory,BUTTON_TEMPLATE);
    }

}
