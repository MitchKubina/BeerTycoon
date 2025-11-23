package BeerTycoon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BeerTycoon {

    public BeerTycoon(String title) {
        JFrame frame = new JFrame(title);
        frame.setSize(400,300);

        JButton button = new JButton("button");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("button pressed");
            }
        });

        frame.add(button);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        BeerTycoon game = new BeerTycoon("poop");
    }

}
