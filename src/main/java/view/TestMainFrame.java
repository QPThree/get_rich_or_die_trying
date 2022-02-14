package view;

import controller.Game;

import javax.swing.*;
import java.awt.*;

public class TestMainFrame{
    static JFrame frame = new JFrame("Game Frame");
    static private Intro intro = new Intro();
    static private Backstory backstory = new Backstory();
    public static Game game = new Game();


    public TestMainFrame(String title) {
        frame = new JFrame(title);
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
                System.out.println("backstory called!");
                removeComponentsFromPane(frame.getContentPane(), frame);
                backstory.render();
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