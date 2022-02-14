package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Backstory {
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    static JFrame frame = TestMainFrame.frame;
    static public ArrayList<JButton> allBackstoryOptionsButtons = new ArrayList<>();
    static public JButton button1 = createJButton("Button 1", 200, 150, false);
    static public JButton button2 = createJButton("Button 2", 200, 150, false);
    static public JButton button3 = createJButton("Button 3", 200, 150, false);
    public static JButton continueButton;
    public static JTextArea textArea;

    public Backstory(){
    }

    public static void backstoryPane(Container pane) {
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

        if (shouldWeightX) {
            c.weightx = 0;
        }

        button = new JButton("Help");
        button.addActionListener(e -> System.out.println("Clicked Help"));
        if (shouldWeightX) {
            c.weightx = 0.5;
        }
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        button.setVisible(true);
        pane.add(button, c);

        button = new JButton("Load");
        button.addActionListener(e -> System.out.println("Clicked Load"));
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        button.setVisible(true);
        pane.add(button, c);

        button = new JButton("exit");
        button.addActionListener(e -> System.out.println("Clicked Exit"));
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        button.setVisible(true);
        pane.add(button, c);

//        button = createJButton("Button 1", 200, 150, false);
        button1.addActionListener(e -> System.out.println("Clicked Button 1"));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 1;
        allBackstoryOptionsButtons.add(button1);
        button1.setVisible(true);
        pane.add(button1, c);


//        button = createJButton("Button 2", 200, 150, false);
        button2.addActionListener(e -> System.out.println("Clicked Button 2"));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 2;
        allBackstoryOptionsButtons.add(button2);
        button2.setVisible(true);
        pane.add(button2, c);

//        button = createJButton("Button 3", 200, 150, false);
        button3.addActionListener(e -> System.out.println("Clicked Button 3"));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 3;
        allBackstoryOptionsButtons.add(button3);
        button3.setVisible(true);
        pane.add(button3, c);

        textArea = new JTextArea();
        textArea.setBackground(Color.white);
        textArea.setVisible(true);
        textArea.setFont(new Font("Hei", Font.BOLD, 22));
        textArea.setPreferredSize(new Dimension(500, 330));
//        textArea.setText("\nWelcome to Get Rich Or Die Trying.\nAt a young age you realize that you want to be a millionaire.\nYour mission is to make $1 million before all your health points run out.\nEach choice you make will affect your net worth and health levels.");
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 3;
        pane.add(textArea, c);

        continueButton = createJButton("Continue", 800, 50, false);
        continueButton.addActionListener(e -> System.out.println("Clicked Continue"));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridheight = 0;
        c.gridwidth = 5;
        c.gridx = 0;
        c.gridy = 4;
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