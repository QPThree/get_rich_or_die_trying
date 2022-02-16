package view;

import controller.Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CareerChoice {
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    static JFrame frame = MainFrame.getInstance();
    static public ArrayList<JButton> allCareerChoiceButtons = new ArrayList<>();
    static public JButton button1 = createJButton("Button 1", 185, 110, false);
    static public JButton button2 = createJButton("Button 2", 185, 110, false);
    static public JButton button3 = createJButton("Button 3", 185, 110, false);
    static public JButton button4 = createJButton("Button 4", 185, 110, false);
    static public JButton button5 = createJButton("Button 5", 185, 110, false);
    static public JButton button6 = createJButton("Button 6", 185, 110, false);
    static public JButton button7 = createJButton("Button 7", 185, 110, false);
    static public JButton button8 = createJButton("Button 8", 185, 110, false);
    static public JButton button9 = createJButton("Button 9", 185, 110, false);
    public static JButton continueButton;
    public static JTextArea textArea;

    public CareerChoice(){
        allCareerChoiceButtons.add(button1);
        allCareerChoiceButtons.add(button2);
        allCareerChoiceButtons.add(button3);
        allCareerChoiceButtons.add(button4);
        allCareerChoiceButtons.add(button5);
        allCareerChoiceButtons.add(button6);
        allCareerChoiceButtons.add(button7);
        allCareerChoiceButtons.add(button8);
        allCareerChoiceButtons.add(button9);

    }

    public static void careerChoicePane(Container pane) {
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

        if (shouldWeightX) {
            c.weightx = 0;
        }


        // Backstory Banner Inserted into page. starts on gridx 0, grid y 0. Spans 4 grids horizontally
//        ImageIcon banner = new ImageIcon(new ImageIcon("resources/backstory.png").getImage().getScaledInstance(800,150,Image.SCALE_AREA_AVERAGING));
//        JLabel bannerLabel = new JLabel();
//        //used for main menu
//        bannerLabel.setIcon(banner);
//        c.gridx = 0;
//        c.gridy = 0;
//        c.gridwidth = 4;
//        pane.add(bannerLabel, c);


//        helpButton = new JButton("Help");
//        helpButton.addActionListener(e -> {
//            String currentSceneText = textArea.getText();
//            System.out.println("Clicked Help");
//            MainFrame.writeToTextArea(textArea, MainFrame.instructions);
//            MainFrame.removeAllActionListeners(helpButton);
//            MainFrame.changeButtonText(helpButton, "Exit Help");
//            helpButton.addActionListener(el -> {
//                MainFrame.writeToTextArea(textArea, currentSceneText);
//                MainFrame.changeButtonText(helpButton, "Help");
//            });
//        });

//        if (shouldWeightX) {
//            c.weightx = 0.5;
//        }
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridx = 0;
//        c.gridy = 1;
//        c.gridwidth = 1;
//        helpButton.setVisible(true);
//        pane.add(helpButton, c);
//
//        button = new JButton("Save");
//        button.addActionListener(e -> Game.saveGame());
//        c.weightx = 0.5;
//        c.gridx = 1;
//        c.gridy = 1;
//        c.fill = GridBagConstraints.HORIZONTAL;
//        button.setVisible(true);
//        pane.add(button, c);
//
//        button = new JButton("Main Menu");
//        button.addActionListener(e -> MainFrame.changeView("intro"));
//        c.weightx = 0.5;
//        c.gridx = 2;
//        c.gridy = 1;
//        c.fill = GridBagConstraints.HORIZONTAL;
//        button.setVisible(true);
//        pane.add(button, c);

//        button = createJButton("Button 1", 200, 150, false);
        button1.addActionListener(e -> System.out.println("Clicked Button 1"));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        allCareerChoiceButtons.add(button1);
        button1.setVisible(true);
        pane.add(button1, c);


//        button = createJButton("Button 2", 200, 150, false);
        button2.addActionListener(e -> System.out.println("Clicked Button 2"));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        allCareerChoiceButtons.add(button2);
        button2.setVisible(true);
        pane.add(button2, c);

//        button = createJButton("Button 3", 200, 150, false);
        button3.addActionListener(e -> System.out.println("Clicked Button 3"));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        allCareerChoiceButtons.add(button3);
        button3.setVisible(true);
        pane.add(button3, c);

        button4.addActionListener(e -> System.out.println("Clicked Button 3"));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        allCareerChoiceButtons.add(button4);
        button4.setVisible(true);
        pane.add(button4, c);

        button5.addActionListener(e -> System.out.println("Clicked Button 3"));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        allCareerChoiceButtons.add(button5);
        button5.setVisible(true);
        pane.add(button5, c);

        button6.addActionListener(e -> System.out.println("Clicked Button 3"));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 1;
        allCareerChoiceButtons.add(button6);
        button6.setVisible(true);
        pane.add(button6, c);

        button7.addActionListener(e -> System.out.println("Clicked Button 3"));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 2;
        allCareerChoiceButtons.add(button7);
        button7.setVisible(true);
        pane.add(button7, c);

        button8.addActionListener(e -> System.out.println("Clicked Button 3"));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 2;
        allCareerChoiceButtons.add(button8);
        button8.setVisible(true);
        pane.add(button8, c);

        button9.addActionListener(e -> System.out.println("Clicked Button 3"));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 2;
        allCareerChoiceButtons.add(button9);
        button9.setVisible(true);
        pane.add(button9, c);


        pane.revalidate();
        pane.setVisible(true);

    }


    private static JButton createJButton(String title, int width, int height, boolean focusable) {
        JButton product = new JButton(title);
        product.setPreferredSize(new Dimension(width, height));
        product.setFocusable(focusable);
        System.out.println("BACKSTORY JBUTTON CREATED");
        return product;
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
        careerChoicePane(frame.getContentPane());

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
