package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Backstory {
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    static JFrame frame = MainFrame.getInstance();
    static public ArrayList<JButton> allBackstoryOptionsButtons = new ArrayList<>();
    static public JButton button1 = createJButton("Button 1", 185, 110, false);
    static public JButton button2 = createJButton("Button 2", 185, 110, false);
    static public JButton button3 = createJButton("Button 3", 185, 110, false);
    public static JButton continueButton;
    public static JTextArea textArea;

    public Backstory(){
    }

    public static void backstoryPane(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        JButton button;
        JButton helpButton;
        pane.setLayout(new GridBagLayout());
        pane.setSize(800,500);
        GridBagConstraints c = new GridBagConstraints(); // if you choose to use the same one throughout, remember to reset values.
        c.insets = new Insets(0,5,0,5);
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }

        if (shouldWeightX) {
            c.weightx = 0;
        }


        // Backstory Banner Inserted into page. starts on gridx 0, grid y 0. Spans 4 grids horizontally
        ImageIcon banner = new ImageIcon(new ImageIcon("resources/backstory.png").getImage().getScaledInstance(800,150,Image.SCALE_AREA_AVERAGING));
        JLabel bannerLabel = new JLabel();
        //used for main menu
        bannerLabel.setIcon(banner);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 4;
        pane.add(bannerLabel, c);


        helpButton = new JButton("Help");
        helpButton.addActionListener(e -> {
            String currentSceneText = textArea.getText();
            System.out.println("Clicked Help");
                MainFrame.writeToTextArea(textArea, MainFrame.instructions);
                MainFrame.removeAllActionListeners(helpButton);
                MainFrame.changeButtonText(helpButton, "Exit Help");
                helpButton.addActionListener(el -> {
                    MainFrame.writeToTextArea(textArea, currentSceneText);
                    MainFrame.changeButtonText(helpButton, "Help");
                });
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

        button = new JButton("Load");
        button.addActionListener(e -> System.out.println("Clicked Load"));
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        button.setVisible(true);
        pane.add(button, c);

        button = new JButton("Main Menu");
        button.addActionListener(e -> MainFrame.changeView("intro"));
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        button.setVisible(true);
        pane.add(button, c);

//        button = createJButton("Button 1", 200, 150, false);
        button1.addActionListener(e -> System.out.println("Clicked Button 1"));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 2;
        allBackstoryOptionsButtons.add(button1);
        button1.setVisible(true);
        pane.add(button1, c);


//        button = createJButton("Button 2", 200, 150, false);
        button2.addActionListener(e -> System.out.println("Clicked Button 2"));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 3;
        allBackstoryOptionsButtons.add(button2);
        button2.setVisible(true);
        pane.add(button2, c);

//        button = createJButton("Button 3", 200, 150, false);
        button3.addActionListener(e -> System.out.println("Clicked Button 3"));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 4;
        allBackstoryOptionsButtons.add(button3);
        button3.setVisible(true);
        pane.add(button3, c);


        textArea = new JTextArea();
        textArea.setBounds(0, 400, 800, 400);
        textArea.setMargin(new Insets(25,100,25,40));
        textArea.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.darkGray,2,true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        textArea.setVisible(true);
//        textArea.setForeground(Color.orange);
//        textArea.setBackground(Color.black);
        textArea.setPreferredSize(new Dimension(450, 250));
        c.gridx = 0;
        c.gridy = 2;
        c.gridheight = 3;
        c.gridwidth = 2;
        pane.add(textArea, c);

        continueButton = createJButton("Continue", 800, 50, false);
        continueButton.addActionListener(e -> System.out.println("Clicked Continue"));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridheight = 0;
        c.gridwidth = 5;
        c.gridx = 0;
        c.gridy = 5;
        pane.add(continueButton, c);
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
        backstoryPane(frame.getContentPane());

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