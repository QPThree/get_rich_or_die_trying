package view;

import controller.Game;

import javax.swing.*;
import java.awt.*;

public class MainFrame {
    public static JFrame frame = null;
    static public Intro intro = new Intro();
    public static Backstory backstory = new Backstory();
    public static Game game = new Game();
    public static LifeStory mainLoop = new LifeStory();


    public MainFrame() {
        getInstance();
        initialize();  //frame boilerplate
    }

    public static JFrame getInstance(){
        if (frame == null){
            frame = new JFrame("Get Rich");
        }
      return frame;
    }

    //boilerplate to set up frame
    private static void initialize() {
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

    //this kicks off the game.
    public static void run() {
        intro.render();
    }

    //used whenever transitioning views
    public static void removeComponentsFromPane(Container pane, JFrame frame) {
        pane.removeAll();
        pane.setVisible(false);
        frame.remove(pane);
        pane.revalidate();
    }

    //change view switches out the view being displayed in the frame.
    //always use removeComponentsFromPane() to clear the frame before displaying new view
    public static void changeView(String view){
        removeComponentsFromPane(frame.getContentPane(), frame);
        switch (view){
            case "intro":
                intro.render();
                break;
            case "backstory":
                backstory.render();
                game.getPlayerBasicData(); //start the game from backstory
                break;

            case "mainLoop":
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


}