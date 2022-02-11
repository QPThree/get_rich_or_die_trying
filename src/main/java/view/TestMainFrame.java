package view;

import javax.swing.*;
import java.awt.*;

import view.Intro;

public class TestMainFrame {
    static JFrame frame = new JFrame("GridBagLayoutDemo");
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

//    public static void removeComponentsFromPane(Container pane) {
//        pane.removeAll();
//        pane.setVisible(false);
//        pane.revalidate();
//    }


//    public static void main(String[] args) {
//        //Schedule a job for the event-dispatching thread:
//        //creating and showing this application's GUI.
////        javax.swing.SwingUtilities.invokeLater(new Runnable() {
////            public void run() {
////                Intro.addComponentsToPane(frame,frame.getContentPane());
////            }
////        });
//    }
}