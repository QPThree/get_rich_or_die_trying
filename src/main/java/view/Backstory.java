package view;

import javax.swing.*;
import java.awt.*;

public class Backstory {
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    public static void addComponentsToPane(Container pane) {
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

        button = new JButton("Help");
        if (shouldWeightX) {
            c.weightx = 0;
        }
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridx = 0;
//        c.gridy = 1;
//        c.gridwidth = 1;
//        pane.add(button, c);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        mb.setSize(800,50);
        JMenu m1 = new JMenu("Options");
        JMenu m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Save Game");
        JMenuItem m22 = new JMenuItem("Quit Game");
        m1.add(m11);
        m1.add(m22);
        c.gridy = 0;
        c.gridx = 0;
        c.ipady = 200;
        pane.add(mb);

    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void renderBackstory() {
        //Create and set up the window.
        JFrame frame = new JFrame("GridBagLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

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

    //    For testing individual page.
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                renderBackstory();
            }
        });
    }
}