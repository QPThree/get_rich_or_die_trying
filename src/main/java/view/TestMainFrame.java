package view;

import controller.Game;

import javax.swing.*;
import java.awt.*;

public class TestMainFrame{
    public static JFrame frame = new JFrame("Game Frame");;
    static public Intro intro = new Intro();
    public static Backstory backstory = new Backstory();
    public static Game game = new Game();
    public static LifeStory mainLoop = new LifeStory();


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
            case "intro":
                System.out.println("Change to intro");
                removeComponentsFromPane(frame.getContentPane(), frame);
                intro.render();
                break;
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
    public void writeToTextArea(JTextArea textArea,String string) {
        //textArea.setPreferredSize(new Dimension(425, 75));
        textArea.removeAll();
        writeToTextArea(textArea, Color.white, string);

    }
    public void writeToTextArea(JTextArea textArea, Color color, String string) {
        textArea.setFont(new Font("Arial", Font.BOLD, 11));
        textArea.setBackground(color);
        textArea.setText(string);
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