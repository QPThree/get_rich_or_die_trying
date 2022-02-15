package view;

import java.awt.*;
import javax.swing.*;

import controller.Game;

public class Intro {
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    static JFrame frame = MainFrame.getInstance();
    public static JTextArea textArea;
    static String welcomeText = "\nWelcome to Get Rich Or Die Trying.\nAt a young age you realize that you want to be a millionaire.\nYour mission is to make $1 million before all your health points run out.\nEach choice you make will affect your net worth and health levels.";

    public static void introPane(Container pane) {
        pane.setVisible(true);
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        JButton button;
        JButton HelpButton;
        pane.setLayout(new GridBagLayout());
        pane.setSize(600,400);
        GridBagConstraints c = new GridBagConstraints(); // if you choose to use the same one throughout, remember to reset values.
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }

//      Banner Inserted into page. starts on gridx 0, grid y 0. Spans 4 grids horizontally
        ImageIcon banner = new ImageIcon(new ImageIcon("resources/GetRichBanner.png").getImage().getScaledInstance(800,150,Image.SCALE_AREA_AVERAGING));
        JLabel bannerLabel = new JLabel();
        //used for main menu
        bannerLabel.setIcon(banner);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 4;
        pane.add(bannerLabel, c);

    // for help functionality
//        exitHelpButton = new JButton("Exit Help");
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridx = 2;
//        c.gridy = 3;
//        c.gridwidth = 1;
//        exitHelpButton.setVisible(false);
//        pane.add(exitHelpButton);

        HelpButton = new JButton("Help");
        HelpButton.addActionListener(e -> {
            MainFrame.writeToTextArea(textArea, MainFrame.instructions);
            MainFrame.removeAllActionListeners(HelpButton);
            MainFrame.changeButtonText(HelpButton, "Exit Help");

            HelpButton.addActionListener(el -> {
                displayWelcomeText();
                MainFrame.changeButtonText(HelpButton, "Help");
            });
            System.out.println(HelpButton.getText());
        /*
            if(Objects.equals(HelpButton.getText(), "Help")){
                TestMainFrame.writeToTextArea(textArea, TestMainFrame.instructions);
                TestMainFrame.removeAllActionListeners(HelpButton);
                TestMainFrame.changeButtonText(HelpButton, "Exit Help");
                HelpButton.addActionListener(el -> {
                    displayWelcomeText();
                    TestMainFrame.changeButtonText(HelpButton, "Help");
                });
            } else {
                System.out.println("different");
            }
            */
        });

        if (shouldWeightX) {
            c.weightx = 0.5;
        }
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        pane.add(HelpButton, c);

        button = new JButton("Load");
        button.addActionListener(e -> System.out.println("Clicked Load"));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 1;
        pane.add(button, c);

        button = new JButton("Exit");
        button.addActionListener(e -> Game.exitGame());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 3;
        c.gridy = 1;
        pane.add(button, c);

        button = new JButton("Play Game");
//        button.addActionListener(e -> backstoryPane(pane));
        button.addActionListener(e -> {
            MainFrame.changeView("backstory"); }); //this starts the game!
        c.fill = GridBagConstraints.HORIZONTAL;
        button.setBackground(Color.green);
        c.ipady = 40;      //make this component tall
        c.weightx = 0.0;
        c.gridwidth = 4;
        c.gridx = 1;
        c.gridy = 2;
        pane.add(button, c);


        textArea = new JTextArea();
        textArea.setBounds(0, 400, 800, 800);
        textArea.setBackground(Color.white);
        textArea.setVisible(true);
        textArea.setFont(new Font("Hei", Font.BOLD, 22));
        textArea.setPreferredSize(new Dimension(800, 400));
        textArea.setText(welcomeText);
        c.gridx = 0;
        c.gridy = 3;
        pane.add(textArea, c);

    }

    private static void displayWelcomeText(){
        textArea.setText(welcomeText);
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
        introPane(frame.getContentPane());

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

//    For testing individual page. This should be exported.
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