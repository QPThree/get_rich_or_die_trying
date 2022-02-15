package view;

import controller.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TestMainFrame{
    public static JFrame frame = new JFrame("Game Frame");;
    static public Intro intro = new Intro();
    public static Backstory backstory = new Backstory();
    public static Game game = new Game();
    public static LifeStory mainLoop = new LifeStory();
    public static String instructions = "Game is meant to simulate life." +
            "\nThe intent of the game is to have 1 million dollars by the end of the game" +
            "\nChoices will change how much money you have, as well as health points." +
            "\nEx: choosing education will grant you an extra money to your salary" +
            "\nbut skipping college will start you out with less debt." +
            "\nChoose carefully, your life depends on it" +
            "\nIf you're done with the help section, press any key to continue.";


    public TestMainFrame() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    public static void removeComponentsFromPane(Container pane, JFrame frame) {
        pane.removeAll();
        pane.setVisible(false);
        frame.remove(pane);
        pane.revalidate();
    }

    public static void changeView(String view){
        switch (view){
            case "backstory":
                removeComponentsFromPane(frame.getContentPane(), frame);
                backstory.render();
                game.getPlayerBasicData(); //start the game
                break;

            case "mainLoop":
                removeComponentsFromPane(frame.getContentPane(), frame);
                mainLoop.render();
                break;
        }

    }


    //Game sends text to translator which sends text to here
    public static void writeToTextArea(JTextArea textArea,String string) {
        //textArea.setPreferredSize(new Dimension(425, 75));
        textArea.removeAll();
        writeToTextArea(textArea, Color.white, string);

    }
    public static void writeToTextArea(JTextArea textArea, Color color, String string) {
        textArea.setFont(new Font("Arial", Font.BOLD, 11));
        textArea.setBackground(color);
        textArea.setText(string);
    }

    public static void removeAllActionListeners(JButton button) {
        for (ActionListener action : button.getActionListeners()) {
            button.removeActionListener(action);
        }
    }

    public static void changeButtonText(JButton button, String text) {
        button.setText(text);
    }

public static void main(String[] args) {
    //Schedule a job for the event-dispatching thread:
    //creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            intro.render();
        }
    });
}
}