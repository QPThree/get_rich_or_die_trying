package view;

import com.sun.tools.javac.Main;
import controller.EffectsTranslator;
import models.Person;
import models.Scene;
import models.SceneContainer;

import javax.swing.*;
import java.awt.*;

public class GameGUI {
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    public static JButton option1, option2, continueButton;
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

        option1 = new JButton(currentScene.getOptions().get(0));
        option1.addActionListener(e -> {
            writeToComponent(sceneInfoTextArea, currentScene.getOutcomes().get(0));
            EffectsTranslator.doEffects(player,currentScene.getEffects().get(1));
            continueButton.addActionListener(el -> {
                System.out.println("lets go!");
                writeToComponent(sceneInfoTextArea,displaySceneSummary(player.addSalary()));
            });
    });
        optionsPanel.add(option1, c);

        option2 = new JButton(currentScene.getOptions().get(1));
        option2.addActionListener(e -> {
            writeToComponent(sceneInfoTextArea, currentScene.getOutcomes().get(1));
            EffectsTranslator.doEffects(player,currentScene.getEffects().get(1));
            continueButton.addActionListener(el -> {
                System.out.println("lets go!");
                writeToComponent(sceneInfoTextArea,displaySceneSummary(player.addSalary()));
//                gameLoop();
            });
        });
        optionsPanel.add(option2, c);

        pane.add(optionsPanel, c);

        continueButton = new JButton("Continue");
        continueButton.setVisible(false);
        optionsPanel.add(continueButton, c);

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

    // so far, dont know how to render data while using the loop
    private static void gameLoop(){

        while (shouldPlay()){
            sceneInfoTextArea.setText(currentScene.getPrompt());
            player.addAge(5);
            System.out.println("working!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }

    private static void writeToComponent(JTextArea textComponent, String text){
        writeToTextArea(textComponent, text);
        textComponent.setLineWrap(true);
        textComponent.setWrapStyleWord(true);
        textComponent.updateUI();
    }

    private static void writeToTextArea(JTextArea textArea,String string) {
        //textArea.setPreferredSize(new Dimension(425, 75));
        textArea.removeAll();
        writeToTextArea(textArea, Color.white, string);
    }

    private static void writeToTextArea(JTextArea textArea, Color color, String string) {
        textArea.setFont(new Font("Arial", Font.BOLD, 11));
        textArea.setBackground(color);
        textArea.setText(string);
        option1.setVisible(false);
        option2.setVisible(false);
        optionsPanel.updateUI();
        showContinueButton();
    }

    private static void showContinueButton() {
        continueButton.setVisible(true);
        continueButton.updateUI();
    }

    private static void hideContinueButton() {
        continueButton.setVisible(false);
        continueButton.updateUI();
    }

    private static String displaySceneSummary(String salaryBreakdown) {
        String values = "";
        System.out.println("\n++++++ 5-Year Summary ++++++");
        System.out.println("Player: " + player.getName());
        System.out.println("Age: " + player.getAge());
        System.out.println("Net Worth: " + player.getPrettyNetWorth());
        System.out.println("Health: " + player.getHealthPoints());
        System.out.println("Children: " + player.getChildren());
        if (player.isMarried()) {
            System.out.println("Spouse: Sam");
        } else {
            System.out.println("Partner: " + (player.getPartner() == null ? "none" : "Sam"));
        }
        System.out.println(salaryBreakdown);

        healthLabel.setText("Health: " + player.getHealthPoints());
        wealthLabel.setText("Net worth: " + player.getPrettyNetWorth());


        // This is currently being used to output the summary.
        // This can go away when serialization is implemented
        values += ("++++++ 5-Year Summary ++++++");
        values += ("\nPlayer: " + player.getName());
        values += ("\nNet Worth: " + player.getPrettyNetWorth());
        values += ("\nChildren: " + player.getChildren());
        if (player.isMarried()) {
            values += ("\nSpouse: " + player.getPartner());
        } else {
            values += ("\nPartner: " + (player.getPartner() == null ? "none" : player.getPartner().getName()));
        }
        return values;
    }

    private static boolean shouldPlay() {
        if (player.getHealthPoints() <= 0) {
            System.out.println("Game Over. You died because you ran out of health points: " + player.getHealthPoints());
            return false;
        }

        if (player.getNetWorth() >= 1000000) {
            System.out.println("You win. You have: " + player.getPrettyNetWorth());
            return false;
        }

        return true;
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