package BeerTycoon;

import BeerTycoon.BeerMakers.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeerTycoon {

    final static Logger logger = LoggerFactory.getLogger(BeerTycoon.class);

    protected double beers = 0;
    JFrame frame;
    MakeBeer beerMaker;

    JLabel beersLabel;

    //Could inject this
    public BeerMaker[] BUTTON_TEMPLATE = {new MakeBeer(), new BeerDude(), new LiquorStore(), new BeerSilo(), new BeerFactory(), new BeerOcean()};

    List<BeerMaker> beerMakers = new ArrayList<>();
    List<JButton> beerMakerButtons = new ArrayList<>();

    List<JPanel> panels = new ArrayList<>();

    public BeerTycoon(String title) {
        beerMaker = new MakeBeer();

        setupScreen(title);
    }

    void setupScreen(String title)  {
        //Dependancy injection here?
        frame = new JFrame(title);
        frame.setSize(800,600);
        frame.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,6));

        for (int i = 0; i < BUTTON_TEMPLATE.length;i++) {
            JButton beerMakerButton = new JButton(BUTTON_TEMPLATE[i].getName());
            buttonPanel.add(beerMakerButton);
            beerMakerButtons.add(beerMakerButton);
        }

        JPanel labelPanel = new JPanel();
        beersLabel = new JLabel();

        String formattedBeers = String.format("Total: %d beers", (int) beers);
        beersLabel.setText(formattedBeers);

        labelPanel.add(beersLabel);

        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(labelPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        beerMakerButtons.get(0).addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                beers += beerMaker.makeBeer();
                System.out.println("Beers: " + beers);

                String formattedBeers = String.format("Total: %d beers", (int) beers);
                beersLabel.setText(formattedBeers);
            }
        });

        setupTimer();
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

        for (int i = 0;i < beerMakerButtons.size();i++) {
            beerMakerButtons.get(i).setEnabled(beers >= BUTTON_TEMPLATE[i].getCost());
        }

        String formattedBeers = String.format("Total: %d beers", (int) beers);
        logger.info(formattedBeers);
        beersLabel.setText(formattedBeers);
    }

    private void addBeer() {
        double calculatedBeers = 0;
        for (BeerMaker beerMaker : beerMakers) {
            calculatedBeers += beerMaker.makeBeer();
        }

        beers += calculatedBeers;
    }

    public static void main(String[] args) {
        BeerTycoon game = new BeerTycoon("poop");
    }

}
