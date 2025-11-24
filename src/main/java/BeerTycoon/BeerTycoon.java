package BeerTycoon;

import BeerTycoon.BeerMakers.MakeBeer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BeerTycoon {

    protected double beers = 0;
    JFrame frame;
    MakeBeer beerMaker;

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

        JButton makeBeerButton = new JButton("Make Beer");
        JButton dudeToGetYouBeer = new JButton("Dude to get you beer");
        JButton liquorStore = new JButton("Liquor Store");
        JButton button1 = new JButton("Test");
        JButton button2 = new JButton("Test");
        JButton button3 = new JButton("test4");

        JPanel labelPanel = new JPanel();
        JLabel beersLabel = new JLabel();

        String formattedBeers = String.format("Total: %d beers", (int) beers);
        beersLabel.setText(formattedBeers);

        labelPanel.add(beersLabel);

        buttonPanel.add(makeBeerButton);
        buttonPanel.add(dudeToGetYouBeer);
        buttonPanel.add(liquorStore);
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);

        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(labelPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        makeBeerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                beers += beerMaker.makeBeer();
                System.out.println("Beers: " + beers);

                String formattedBeers = String.format("Total: %d beers", (int) beers);
                beersLabel.setText(formattedBeers);
            }
        });
    }

    public static void main(String[] args) {
        BeerTycoon game = new BeerTycoon("poop");
    }

}
