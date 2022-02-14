package controller;

import view.MainFrame;
import view.TestMainFrame;

import javax.swing.*;

/*
  class that converts Game.java logic to GUI display, and User-GUI interactions to Game.java logic.
 */
public class GUILogicTranslator {
    private Game game;
    private MainFrame mainframe;
    TestMainFrame frame;


    public GUILogicTranslator(Game game, TestMainFrame frame){
        setGame(game);
        setMainframe(frame);
    }

    private void setGame(Game game) {
        this.game = game;
    }

    private void setMainframe(TestMainFrame frame) {
        this.frame = frame;
    }

    /* 1. DONE: writeToComponent(JTextArea comp, String text) -> writes text to textarea component
    *  2. addEventListener(Component comp, Callback function) -> wire buttons to functions in Game. Need callback alternative for Java
    *  3. editButtonText(Component comp, String text) -> change button text to be displayed to user
     */

    //used to write to text areas in gui
    public void writeToComponent(JTextArea textComponent, String text){
        frame.writeToTextArea(textComponent, text);
        textComponent.setLineWrap(true);
        textComponent.setWrapStyleWord(true);
        textComponent.updateUI();
    }

    public void editButtonText(JButton button, String text){
        System.out.println("Setting text at " + button.getText());
        button.setText(text);
        button.updateUI();
        System.out.println("SETTING TEXT " + text);
    }

    public void addEventListener(JComponent comp){

    }

}
