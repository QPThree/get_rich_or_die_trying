package view;

import com.sun.tools.javac.Main;
import models.Person;
import models.Scene;
import models.SceneContainer;

import javax.swing.*;
import java.awt.*;

public class GameGUI {
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    public static JButton option1, option2;
    public static JPanel attributesPanel, playerInfoPanel, sceneInfo, healthPanel, wealthPanel, optionsPanel, sceneArt, oneOfTwoOptionsPanel;
    public static JTextArea textArea = new JTextArea(20, 20), playerInfoTextArea = new JTextArea(5,5), sceneInfoTextArea = new JTextArea(20,20), optionsTextArea = new JTextArea(5,20), playerAttributes = new JTextArea(5, 20);
    public static JLabel attributesLabel = new JLabel(), healthLabel = new JLabel(), wealthLabel = new JLabel();
    private static Person player = new Person();
    static SceneContainer scene = new SceneContainer();
    static Scene currentScene = scene.getRandomScene(player);
    MainFrame mainFrame = new MainFrame();

    public static void addGameComponentsToPane(Container pane) {
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

        // instantiating player data for testing
        testPlayer();

        playerInfoTextArea.setBounds(0, 50, 100, 100);
        playerInfoTextArea.setBackground(Color.gray);
        playerInfoTextArea.setVisible(true);
        playerInfoTextArea.setFont(new Font("Hei", Font.BOLD, 12));
        playerInfoTextArea.setPreferredSize(new Dimension(300, 100));
        playerInfoTextArea.setText("Name: " + player.getName() +
                "\nAge: " + player.getAge()
                + "\nCareer Choice: " + player.getCareer());
        c.gridx = 0;
        c.gridy = 0;
        pane.add(playerInfoTextArea, c);

        playerAttributes.setBounds(0, 50, 100, 100);
        playerAttributes.setBackground(Color.blue);
        playerAttributes.setVisible(true);
        playerAttributes.setFont(new Font("Hei", Font.BOLD, 12));
        playerAttributes.setPreferredSize(new Dimension(300, 100));
        playerAttributes.setText("Strength: " + player.getStrength() +
                " Intellect: " + player.getIntellect() + " Creativity: " + player.getCreativity());

        c.gridx = 1;
        c.gridy = 0;
        pane.add(playerAttributes, c);

//        attributesLabel.setText("Strength: " + player.getStrength() +
//                " Intellect: " + player.getIntellect() + " Creativity: " + player.getCreativity());
//        c.gridx = 0;
//        c.gridy = 0;
//        c.gridwidth = 4;
//        pane.add(attributesLabel, c);


        sceneInfoTextArea.setBounds(0, 400, 800, 800);
        sceneInfoTextArea.setBackground(Color.pink);
        sceneInfoTextArea.setVisible(true);
        sceneInfoTextArea.setFont(new Font("Hei", Font.BOLD, 12));
        sceneInfoTextArea.setPreferredSize(new Dimension(200, 200));
        sceneInfoTextArea.setWrapStyleWord(true);
        sceneInfoTextArea.setLineWrap(true);
        sceneInfoTextArea.setText(currentScene.getPrompt());
        c.gridx = 0;
        c.gridy = 1;
        pane.add(sceneInfoTextArea, c);

        //creating the options panel & buttons
        optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(2,1,0,20));
        c.gridy = 1;
        c.gridx = 1;
        optionsPanel.setBackground(Color.green);

        option1 = new JButton("Option 1");
        option1.addActionListener(e -> System.out.println("Clicked 1"));
        optionsPanel.add(option1, c);

        option2 = new JButton("Option 2");
        option2.addActionListener(e -> System.out.println("Clicked 2"));
        optionsPanel.add(option2, c);

        pane.add(optionsPanel, c);

        healthLabel.setText("Health: " + player.getHealthPoints());
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 4;
        pane.add(healthLabel, c);

        wealthLabel.setText("Net worth: " + player.getPrettyNetWorth());
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 4;
        pane.add(wealthLabel, c);
    }



    private static void renderGame() {
        //Create and set up the window.
        JFrame frame = new JFrame("GridBagLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        addGameComponentsToPane(frame.getContentPane());

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

    private static void testPlayer () {
        player.setName("DEV");
        player.setPrivilege(true);
        player.setEducation(true);
        player.addStrength(5);
        player.addIntellect(5);
        player.addCreativity(5);
    }

    //    For testing individual page. This should be exported.
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                renderGame();
            }
        });
    }
}