package BeerTycoon;

import BeerTycoon.BeerMakers.BeerMaker;
import BeerTycoon.BeerMakers.MakeBeer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

//singleton class?
public class BeerTycoonGUI {

    private static BeerTycoonGUI guiInstance;

    private JFrame frame = new JFrame();
    private JLabel beersLabel = new JLabel();
    private JPanel labelPanel = new JPanel();
    private List<JButton> beerMakerButtons = new ArrayList<JButton>();
    private List<BeerMaker> beerMakers;
    private BeerTycoon beerTycoon;
    private JPanel buttonPanel = new JPanel();
    private JPanel messagePanel = new JPanel();
    private JLabel messageLabel = new JLabel();
    private JPanel middlePanel = new JPanel();

    public void setGame(BeerTycoon beerTycoon) {
        this.beerTycoon = beerTycoon;
    }

    public void setButtons(List<BeerMaker> buttonList) {
        int totalButtons = buttonList.size();
        this.beerMakers = buttonList;

        frame = new JFrame("Beer Tycoon");
        frame.setSize(800,600);
        frame.setLayout(new BorderLayout());

        buttonPanel.setLayout(new GridLayout(1,totalButtons));

        for (int i = 0; i < totalButtons;i++) {
            JButton beerMakerButton = new JButton(buttonList.get(i).getName());
            buttonPanel.add(beerMakerButton);
            beerMakerButtons.add(beerMakerButton);
        }

        setBeers(0);

        labelPanel.add(beersLabel);
        setButtonFunctions();
    }

    private void setButtonFunctions() {
        int numButtons = beerMakerButtons.size();
        for (int i = 1;i < numButtons;i++){
            String beerMakerString = beerMakers.get(i).getName();
            beerMakerButtons.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    beerTycoon.addBeerMaker(beerMakerString);
                }
            });
        }

        beerMakerButtons.getFirst().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //antipattern type shit
                System.out.println("clicked");
                beerTycoon.addBeers(1.0);
            }
        });
    }

    public void setBeers(double beers) {
        for (int i = 0;i < beerMakerButtons.size();i++) {
            beerMakerButtons.get(i).setEnabled(beers >= beerMakers.get(i).getCost());
        }

        String formattedBeers = String.format("Total: %d beers", (int) beers);
        beersLabel.setText(formattedBeers);
    }

    //sets us up to have some observers
    public void setMessage(String message) {
        messageLabel.setText(message);
    }

    private BeerTycoonGUI() {}

    public static BeerTycoonGUI getInstance() {
        if (guiInstance == null) {
            guiInstance = new BeerTycoonGUI();
        }
        return guiInstance;
    }

    public void showScreen() {
        frame.add(buttonPanel, BorderLayout.SOUTH);

        middlePanel.setLayout(new BoxLayout(middlePanel,BoxLayout.PAGE_AXIS));
        messagePanel.add(messageLabel);
        setMessage("hi there");

        middlePanel.add(labelPanel);
        middlePanel.add(messagePanel);
        frame.add(middlePanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


}
