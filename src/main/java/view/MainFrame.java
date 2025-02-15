package view;

import controller.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainFrame {
    private static JFrame frame = null; //singleton from getInstance()
    public static Intro intro = new Intro();
    public static Backstory backstory = new Backstory();
    public static Game game = new Game();
    public static CareerChoice careerChoice = new CareerChoice();
    public static LifeStory mainLoop = new LifeStory();
    public static GameOver gameOver = new GameOver();
    public static Winner winner = new Winner();
    public static String instructions = "Get RICH or Die Trying!" +
            "\n\n* Try to accumulate a net worth of 1 million dollars by the end of the game." +
            "\n* Choices will change how much money you have, as well as health points." +
            "\n* Choose carefully, your LIFE depends on it" +
            "\n\n[Exit Help] to continue.";
    // colors for buttons
    public static final String CONTINUE_BUTTON_COLOR = "#2a9d8f";
    public static final String HELP_BUTTON_COLOR = "#ee9b00";
    public static final String EXIT_BUTTON_COLOR = "#ae2012";
    public static final String LOAD_BUTTON_COLOR = "#001219";
    public static final String CHOICE_BUTTON_COLOR = "#3d405b";

    public MainFrame() {
        getInstance();
        initialize();  //frame boilerplate
    }

    //singleton frame is handled here
    public static JFrame getInstance(){
        if (frame == null){
            frame = new JFrame("Get Rich or Die Trying");
        }
        return frame;
    }

    //boilerplate to set up frame
    private static void initialize() {
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

    //this kicks off the game.
    public static void run() {
        intro.render();
    }

    //used whenever transitioning views
    public static void removeComponentsFromPane(Container pane, JFrame frame) {
        pane.removeAll();
        pane.setVisible(false);
        frame.remove(pane);
        pane.revalidate();
    }

    //change view switches out the view being displayed in the frame.
    //always use removeComponentsFromPane() to clear the frame before displaying new view
    public static void changeView(String view){
        removeComponentsFromPane(frame.getContentPane(), frame);
        switch (view){
            case "intro":
                intro.render();
                break;
            case "backstory":
                backstory.render();
                game.getPlayerBasicData(); //start the game from backstory
                break;
            case "careerChoice":
                careerChoice.render();
                break;

            case "mainLoop":
                mainLoop.render();
                mainLoop.continueButton.setVisible(false);
                break;

            case "gameOver":
                gameOver.render();
                break;

            case "winner":
                winner.render();
                break;
        }

    }


    //Game sends text to translator which sends text to here
    public static void writeToTextArea(JTextArea textArea,String string) {
        //textArea.setPreferredSize(new Dimension(425, 75));
        textArea.removeAll();
        writeToTextArea(textArea, Color.white, string);

    }

    public static void writeToTextArea(JTextArea textArea, Color color, String string) {
        //Euphemia UCAS

        textArea.setFont(new Font("Euphemia UCAS", Font.BOLD, 18));

        textArea.setBackground(color);
        textArea.setText(string);
    }

    public static void removeAllActionListeners(JButton button) {
        for (ActionListener action : button.getActionListeners()) {
            button.removeActionListener(action);
        }
    }

    public static void changeButtonText(JButton button, String text) {
        button.setText(text);
    }

    // Responsible for toggling the help menu
    /*
    public static void helpToggler(JButton HelpButton, JTextArea textArea, String previousText){
        if(HelpButton.getText().equals("Help")) {
            MainFrame.writeToTextArea(textArea, MainFrame.instructions);
            MainFrame.changeButtonText(HelpButton, "Exit Help");
//            MainFrame.removeAllActionListeners(HelpButton);
            HelpButton.addActionListener(e -> {
                MainFrame.writeToTextArea(textArea,previousText);
                MainFrame.changeButtonText(HelpButton,"Help");
                System.out.println("1");
//                helpToggler(HelpButton,textArea,MainFrame.instructions);
            });
        } else {
            System.out.println(previousText);
            textArea.setText(previousText);
            MainFrame.changeButtonText(HelpButton, "Help");
        }
    }
*/
    public static void helpToggler(JButton HelpButton, JTextArea textArea, String previousText){
        if(HelpButton.getText().equals("Help")) {
            MainFrame.writeToTextArea(textArea, MainFrame.instructions);
            MainFrame.changeButtonText(HelpButton, "Exit Help");
        } else {
            System.out.println(previousText);
            textArea.setText(previousText);
            MainFrame.changeButtonText(HelpButton, "Help");
        }
    }
}