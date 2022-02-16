package view;

import controller.Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static controller.Game.exitGame;
import static controller.Game.player;

public class GameOver {

    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    static JFrame frame = MainFrame.getInstance();
    public static JButton playAgainButton, exitButton;
    public static JTextArea textArea;

    public GameOver() {
    }

    public static void backstoryPane(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        pane.setLayout(new GridBagLayout());
        pane.setSize(800, 500);
        GridBagConstraints c = new GridBagConstraints(); // if you choose to use the same one throughout, remember to reset values.
        c.insets = new Insets(0, 5, 0, 5);
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }

        if (shouldWeightX) {
            c.weightx = 0;
        }


        // Backstory Banner Inserted into page. starts on gridx 0, grid y 0. Spans 4 grids horizontally
        ImageIcon banner = new ImageIcon(new ImageIcon("resources/GameOver.png").getImage().getScaledInstance(800, 150, Image.SCALE_AREA_AVERAGING));
        JLabel bannerLabel = new JLabel();
        //used for setting the game over image
        bannerLabel.setIcon(banner);
        c.insets = new Insets(0, 5, 5, 5);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 4;
        pane.add(bannerLabel, c);

        // defining the text area for the game over info
        textArea = new JTextArea();
        textArea.setBounds(0, 400, 800, 300);
        textArea.setMargin(new Insets(25, 100, 25, 5));
        textArea.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.darkGray, 2, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setVisible(true);
        textArea.setPreferredSize(new Dimension(450, 200));
        //setting text here for testing purposes - this will eventually come from the game
        textArea.setText("Game Over. You died because you ran out of health points: " + player.getHealthPoints());
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 3;
        c.gridwidth = 1;
        pane.add(textArea, c);

        playAgainButton = createJButton("Play Again", 185, 50, false);
        playAgainButton.addActionListener(e -> MainFrame.changeView("intro"));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 4;
        playAgainButton.setVisible(true);
        pane.add(playAgainButton, c);

        exitButton = createJButton("Exit Game", 185, 50, false);
        exitButton.addActionListener(e -> Game.exitGame());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 7;
        exitButton.setVisible(true);
        pane.add(exitButton, c);

        pane.revalidate();
        pane.setVisible(true);
    }


    private static JButton createJButton(String title, int width, int height, boolean focusable) {
        JButton product = new JButton(title);
        product.setPreferredSize(new Dimension(width, height));
        product.setFocusable(focusable);
        System.out.println("BACKSTORY JBUTTON CREATED");
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

    //    For testing individual page. This needs to be removed once functionality is connected to the rest of the game
//    public static void main(String[] args) {
//        //Schedule a job for the event-dispatching thread:
//        //creating and showing this application's GUI.
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                render();
//            }
//        });
//    }
}