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
    JFrame frame;
    MakeBeer beerMaker;
    BeerMakerFactory beerMakerFactory = new BeerMakerFactory();
    JLabel beersLabel;
    BeerTycoonGUI gui;

    //Could inject this
    //ALso should probably be in string format and we use factories
    //public BeerMaker[] BUTTON_TEMPLATE = {new MakeBeer(), new BeerDude(), new LiquorStore(), new BeerSilo(), new BeerFactory(), new BeerOcean()};
    public List<BeerMaker> BUTTON_TEMPLATE = Arrays.asList(new MakeBeer(), new BeerDude(), new LiquorStore(), new BeerSilo(), new BeerFactory(), new BeerOcean());

    List<BeerMaker> beerMakers = new ArrayList<>();
    List<JButton> beerMakerButtons = new ArrayList<>();

    List<JPanel> panels = new ArrayList<>();

    public BeerTycoon(String title) {
        beerMaker = new MakeBeer();

        setupScreen();
        setupTimer();
    }

    void setupScreen()  {
       gui = BeerTycoonGUI.getInstance();
       gui.setGame(this);
       gui.setButtons(BUTTON_TEMPLATE);
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
        BeerTycoon game = new BeerTycoon("This does nothing right now");
    }

}
