package view;

import controller.EffectsTranslator;
import controller.Game;
import models.Person;
import models.Scene;
import models.SceneContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LifeStory {
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    static JFrame frame = MainFrame.getInstance(); //from quen/zach branch
    public static JButton option1 = createJButton("Button 1", 200, 150, false);
    public static JButton option2 = createJButton("Button 2", 200, 150, false);
    public static JButton continueButton;
    public static JPanel optionsPanel;
    public static JTextArea textArea = new JTextArea(20, 20), playerInfoTextArea = new JTextArea(5,5), sceneInfoTextArea = new JTextArea(20,20), playerAttributes = new JTextArea(5, 20);
    public static JLabel healthLabel = new JLabel(), wealthLabel = new JLabel();
    private static Person player = Game.player;
    static SceneContainer scene = new SceneContainer();

    public static void addGameComponentsToPane(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        JButton button;
        pane.setLayout(new GridBagLayout());
        pane.setSize(800,500);
        GridBagConstraints c = new GridBagConstraints(); // if you choose to use the same one throughout, remember to reset values.
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }

        playerInfoTextArea.setBackground(Color.gray);
        playerInfoTextArea.setVisible(true);
        playerInfoTextArea.setFont(new Font("Hei", Font.BOLD, 12));
        playerInfoTextArea.setPreferredSize(new Dimension( 550, 100));
        playerInfoTextArea.setText("Name: " + player.getName() +
                "  Age: " + player.getAge()
                + "  Career Choice: " + player.getCareer() +
                " NetWorth: " + player.getPrettyNetWorth() +
                " Health: " + player.getHealthPoints());
        c.gridx = 0;
        c.gridy = 0;
        pane.add(playerInfoTextArea, c);

        playerAttributes.setBackground(Color.blue);
        playerAttributes.setVisible(true);
        playerAttributes.setFont(new Font("Hei", Font.BOLD, 12));
        playerAttributes.setPreferredSize(new Dimension(250, 100));
        playerAttributes.setText("Strength: " + player.getStrength() +
                " Intellect: " + player.getIntellect() + " Creativity: " + player.getCreativity());

        c.gridx = 1;
        c.gridy = 0;
        pane.add(playerAttributes, c);

        sceneInfoTextArea.setBackground(Color.pink);
        sceneInfoTextArea.setVisible(true);
        sceneInfoTextArea.setFont(new Font("Hei", Font.BOLD, 16));
        sceneInfoTextArea.setPreferredSize(new Dimension(400, 200));
        sceneInfoTextArea.setWrapStyleWord(true);
        sceneInfoTextArea.setLineWrap(true);
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 2;
        pane.add(sceneInfoTextArea, c);

        // Option1 grid
        c.gridx = 1;
        c.gridy = 1;
        c.gridheight = 1;
        pane.add(option1, c);

        //Option 2 grid
        c.gridx= 1;
        c.gridy = 2;
        c.gridheight = 1;
        pane.add(option2, c);

        continueButton = createJButton("Continue", 800, 50, false);
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        pane.add(continueButton, c);


        pane.revalidate();
        pane.setVisible(true);
    }

    private static JButton createJButton(String title, int width, int height, boolean focusable) {
        JButton product = new JButton(title);
        product.setPreferredSize(new Dimension(width, height));
        product.setFocusable(focusable);
        return product;
    }


    static void render() {
        System.out.println("****RUNNING MAIN LOOP! ****");
        //Create and set up the window.
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

}