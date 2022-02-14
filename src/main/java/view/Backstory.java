package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Backstory {
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    static JFrame frame = TestMainFrame.frame;
    static public ArrayList<JButton> allBackstoryOptionsButtons = new ArrayList<>();
    static JButton continueButton;

    public static void backstoryPane(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        JButton button;
        pane.setLayout(new GridBagLayout());
        pane.setSize(600,400);
        GridBagConstraints c = new GridBagConstraints(); // if you choose to use the same one throughout, remember to reset values.
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }

        if (shouldWeightX) {
            c.weightx = 0;
        }



        button = new JButton("Help");
        button.addActionListener(e -> System.out.println("Clicked Help"));
        if (shouldWeightX) {
            c.weightx = 0.5;
        }
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        pane.add(button, c);

        button = new JButton("Load");
        button.addActionListener(e -> System.out.println("Clicked Load"));
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        pane.add(button, c);

        button = new JButton("exit");
        button.addActionListener(e -> System.out.println("Clicked Exit"));
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        pane.add(button, c);


        button = createJButton("Button 1", 200, 150, false);
        button.addActionListener(e -> System.out.println("Clicked Button 1"));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 1;
        allBackstoryOptionsButtons.add(button);
        pane.add(button, c);


        button = createJButton("Button 2", 200, 150, false);
        button.addActionListener(e -> System.out.println("Clicked Button 2"));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 2;
        allBackstoryOptionsButtons.add(button);
        pane.add(button, c);

        button = createJButton("Button 3", 200, 150, false);
        button.addActionListener(e -> System.out.println("Clicked Button 3"));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 3;
        allBackstoryOptionsButtons.add(button);
        pane.add(button, c);

        JTextArea product = new JTextArea();
        product.setBackground(Color.white);
        product.setVisible(true);
        product.setFont(new Font("Hei", Font.BOLD, 22));
        product.setPreferredSize(new Dimension(500, 330));
        product.setText("\nWelcome to Get Rich Or Die Trying.\nAt a young age you realize that you want to be a millionaire.\nYour mission is to make $1 million before all your health points run out.\nEach choice you make will affect your net worth and health levels.");
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 3;
        pane.add(product, c);

        continueButton = createJButton("Continue", 800, 50, false);
        continueButton.addActionListener(e -> System.out.println("Clicked Continue"));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridheight = 0;
        c.gridwidth = 5;
        c.gridx = 0;
        c.gridy = 4;
        pane.add(continueButton, c);
        pane.setVisible(true);
    }


    private static JButton createJButton(String title, int width, int height, boolean focusable) {
        JButton product = new JButton(title);
        product.setPreferredSize(new Dimension(width, height));
        product.setFocusable(focusable);
        return product;
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public static void render() {
        //Create and set up the window.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        backstoryPane(frame.getContentPane());
        System.out.println("COMING FROM BACKSTORY CLASS NOW");
        //Display the window.

        frame.pack();
        frame.setSize(800, 600); // width
        frame.setResizable(false); // enable the resize of the frame
        frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// enable close
        frame.getContentPane().setBackground(Color.white);// background color
        frame.setLayout(null);
        frame.setVisible(true);
    }


}