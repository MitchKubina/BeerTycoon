package beerTycoon;

import beerTycoon.beerMakers.BeerMaker;
import beerTycoon.beerMakers.BeerMakerType;
import beerTycoon.observers.AudibleGameObserver;
import beerTycoon.upgrades.UpgradeType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static beerTycoon.BeerTycoon.UPGRADE_COST;

//singleton class?
public class BeerTycoonGUI {

    private static BeerTycoonGUI guiInstance;

    static final int STARTING_GAME_STATISTICS_COUNTS = 0;

    AudibleGameObserver observer;

    private JFrame frame = new JFrame();

    private JLabel beersLabel = new JLabel();
    private JLabel messageLabel = new JLabel();

    private JPanel middlePanel = new JPanel();
    private JPanel labelPanel = new JPanel();

    JTextArea textArea = new JTextArea(11, 20);

    private List<JButton> beerMakerButtons = new ArrayList<>();
    private List<JButton> upgradeButtons = new ArrayList<>();

    private List<BeerMaker> beerMakers;
    private List<UpgradeType> upgradeTypes;

    private BeerTycoon beerTycoon;
    private JPanel buttonPanel = new JPanel();
    private JPanel upgradePanel = new JPanel();
    private JPanel messagePanel = new JPanel();

    private BeerTycoonGUI() {}

    public static BeerTycoonGUI getInstance() {
        if (guiInstance == null) {
            guiInstance = new BeerTycoonGUI();
        }
        return guiInstance;
    }

    public void initializeGame(BeerTycoon beerTycoon, List<BeerMaker> beerMakerShoppingCatalog, List<UpgradeType> upgradeButtons) {
        setGame(beerTycoon);
        setUpFrame();
        setButtons(beerMakerShoppingCatalog, upgradeButtons);
        showScreen();
    }

    private void setGame(BeerTycoon beerTycoon) {
        this.beerTycoon = beerTycoon;
    }

    private void setButtons(List<BeerMaker> makerList, List<UpgradeType> upgradeList) {
        this.beerMakers = makerList;
        this.upgradeTypes = upgradeList;

        beerMakerButtons.clear();
        upgradeButtons.clear();
        buttonPanel.removeAll();
        upgradePanel.removeAll();

        // Setup Beer Producer Buttons
        buttonPanel.setLayout(new GridLayout(1, makerList.size()));
        for (BeerMaker maker : makerList) {
            JButton btn = new JButton(maker.getName() + " (Cost: " + (int) maker.getCost() + ")");
            buttonPanel.add(btn);
            beerMakerButtons.add(btn);
        }

        // Setup Upgrade Buttons
        upgradePanel.setLayout(new FlowLayout());
        for (UpgradeType type : upgradeList) {
            JButton btn = new JButton("Buy " + type.name() + " Upgrade " + UPGRADE_COST);
            upgradePanel.add(btn);
            upgradeButtons.add(btn);
        }

        labelPanel.add(beersLabel);
        setButtonFunctions();
    }

    private void setUpFrame() {
        frame = new JFrame("Beer Tycoon");
        frame.setSize(1200,300);
        frame.setLayout(new BorderLayout());
    }

    private void setButtonFunctions() {
        // Beer Producer Buttons Logic
        setBeerMakerButtons();
        // Upgrade Buttons Logic
        setUpgradeButtons();
    }

    private void setUpgradeButtons() {
        for (int i = 0; i < upgradeButtons.size(); i++) {
            final UpgradeType type = upgradeTypes.get(i);
            upgradeButtons.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Using BeerTycoon to hangle game logic
                    beerTycoon.handleUpgradeAction(type);
                }
            });
        }
    }

    private void setBeerMakerButtons() {
        for (int i = 0; i < beerMakerButtons.size(); i++) {
            final int index = i;
            beerMakerButtons.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = beerMakers.get(index).getName();
                    BeerMakerType type = beerMakerNameToType(name);

                    if (type != null) {
                        //Using BeerTycoon to hangle game logic
                        handleMakerButtonAction(type);
                    }
                }
            });
        }
    }

    private void handleMakerButtonAction(BeerMakerType type) {
        if (type == BeerMakerType.MakeBeer) {
            beerTycoon.manuallyMakeBeers();
        } else {
            beerTycoon.buyAndAddBeerMaker(type);
        }
    }

    private BeerMakerType beerMakerNameToType(String name) {
        //Normalizing the string so that the enum will match even if the string has spaces or capitalization
        String normalizedName = name.replaceAll("\\s+", "");

        for (BeerMakerType type : BeerMakerType.values()) {
            if (type.name().equals(normalizedName)) {
                return type;
            }
        }
        return null;
    }

    public void updateBeerCount(double beers) {
        updateBeerMakerPurchasablity(beers);
        String formattedBeers = String.format("Total: %d beers", (int) beers);
        beersLabel.setText(formattedBeers);
    }

    private void updateBeerMakerPurchasablity(double beers) {
        for (int i = 0; i < beerMakerButtons.size(); i++) {
            beerMakerButtons.get(i).setEnabled(beers >= beerMakers.get(i).getCost());
        }
    }

    private void showScreen() {
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(upgradePanel, BorderLayout.EAST);

        middlePanel.setLayout(new BoxLayout(middlePanel,BoxLayout.PAGE_AXIS));
        messagePanel.add(messageLabel);

        updateGameStatisticsMessage(STARTING_GAME_STATISTICS_COUNTS, STARTING_GAME_STATISTICS_COUNTS);

        middlePanel.add(textArea);
        middlePanel.add(labelPanel);
        middlePanel.add(messagePanel);
        frame.add(middlePanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    //TODO: make observer for beer clicks and/or all game counts/data and update GUI accordingly
    public void updateGameStatisticsMessage(int beerMakersCount, int upgradesCount) {
        textArea.setText("BEER!" +
                "\nBeer Makers: " + beerMakersCount +
                "\n Upgrades: " + upgradesCount);
    }

}
