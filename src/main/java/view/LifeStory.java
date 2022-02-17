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
    public static JTextArea textArea = new JTextArea(20, 20), playerInfoTextArea = new JTextArea(5,5), sceneInfoTextArea = new JTextArea(20,20), playerAttributes = new JTextArea(5, 20);
    public static JLabel healthLabel = new JLabel(), wealthLabel = new JLabel();
    private static Person player = Game.player;
    static SceneContainer scene = new SceneContainer();
    static String currentSceneText;

    public static void addGameComponentsToPane(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        JButton button;
        JButton helpButton;
        pane.setLayout(new GridBagLayout());
        pane.setSize(800,500);
        GridBagConstraints c = new GridBagConstraints(); // if you choose to use the same one throughout, remember to reset values.
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }


        playerInfoTextArea.setMargin(new Insets(25,100,25,40));
        playerInfoTextArea.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.darkGray,2,true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        playerInfoTextArea.setVisible(true);
        playerInfoTextArea.setFont(new Font("Euphemia UCAS", Font.BOLD, 14));
        playerInfoTextArea.setPreferredSize(new Dimension( 700, 100));
        playerInfoTextArea.setText( "\n" +
                " | Name: " + player.getName() +
                " | Age: " + player.getAge() +
                " | Career Choice: " + player.getCareer() +
                " | NetWorth: " + player.getPrettyNetWorth() +
                " | Health: " + player.getHealthPoints() + " |\n\n" +
                " | Strength: " + player.getStrength() +
                " | Intellect: " + player.getIntellect() +
                " | Creativity: " + player.getCreativity() + " | \n"
        );
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.insets = new Insets(10,7,2,15);
        pane.add(playerInfoTextArea, c);


        helpButton = new JButton("Help");
        helpButton.setForeground(Color.decode(MainFrame.HELP_BUTTON_COLOR));
        helpButton.addActionListener(e -> {
            if(!sceneInfoTextArea.getText().equals(MainFrame.instructions)){
                currentSceneText = sceneInfoTextArea.getText();
            }
            MainFrame.helpToggler(helpButton,sceneInfoTextArea,currentSceneText);
        });

        if (shouldWeightX) {
            c.weightx = 0.5;
        }
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        helpButton.setVisible(true);
        pane.add(helpButton, c);

        button = new JButton("Save");
        button.setForeground(Color.decode(MainFrame.LOAD_BUTTON_COLOR));
        button.addActionListener(e -> System.out.println("Clicked Save"));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;

        c.gridx = 1;
        c.gridy = 1;
        button.setVisible(true);
        pane.add(button, c);

        button = new JButton("Main Menu");
        button.setBackground(Color.decode("#FFFFFF"));
        button.setForeground(Color.decode(MainFrame.EXIT_BUTTON_COLOR));
        button.addActionListener(e -> MainFrame.changeView("intro"));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 1;
        button.setVisible(true);
        pane.add(button, c);

        sceneInfoTextArea.setMargin(new Insets(0,100,25,40));
        sceneInfoTextArea.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.darkGray,2,true),

        BorderFactory.createEmptyBorder(25, 15, -200, 18)));


        sceneInfoTextArea.setVisible(true);
        sceneInfoTextArea.setFont(new Font("Hei", Font.BOLD, 18));
        sceneInfoTextArea.setPreferredSize(new Dimension(400, 200));
        sceneInfoTextArea.setWrapStyleWord(true);
        sceneInfoTextArea.setLineWrap(true);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.gridheight = 2;
        c.insets = new Insets(2,5,5,2);
        pane.add(sceneInfoTextArea, c);

        // Option1 grid
        c.gridx = 2;
        c.gridy = 2;
        c.gridheight = 1;
        pane.add(option1, c);

        //Option 2 grid
        c.gridx= 2;
        c.gridy = 3;
        c.gridheight = 1;
        pane.add(option2, c);

        continueButton = createJButton("Continue", 200, 75, false);
        continueButton.setForeground(Color.decode(MainFrame.CONTINUE_BUTTON_COLOR));
        button.setOpaque(true);
        continueButton.addActionListener(e -> System.out.println("Clicked Continue from LifeStory"));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridheight = 0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 5;
        c.insets = new Insets(0,100,0,100);
        pane.add(continueButton, c);

        pane.revalidate();
        pane.setVisible(true);
        playerInfoTextArea.setEditable(false);
        sceneInfoTextArea.setEditable(false);

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

    //    For testing individual page. This should be exported.
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                render();
            }
        });
    }

}