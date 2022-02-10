package controller;

import view.MainFrame;

import javax.swing.*;

/*
  class that converts Game.java logic to GUI display, and User-GUI interactions to Game.java logic.
 */
class GUILogicTranslator {
    private Game game;
    private MainFrame mainframe;


    public GUILogicTranslator(Game game, MainFrame mainframe){
        setGame(game);
        setMainframe(mainframe);
    }
    public void setGame(Game game) {
        this.game = game;
    }

    public void setMainframe(MainFrame mainframe) {
        this.mainframe = mainframe;
    }

    /* 1. DONE: writeToComponent(JTextArea comp, String text) -> writes text to textarea component
    *  2. addEventListener(Component comp, Callback function) -> wire buttons to functions in Game. Need callback alternative for Java
    *  3. editButtonText(Component comp, String text) -> change button text to be displayed to user
     */

    //used to write to text areas in gui
    public void writeToComponent(JTextArea textComponent, String text){
        mainframe.writeToTextArea(textComponent, text);
        textComponent.setLineWrap(true);
        textComponent.setWrapStyleWord(true);
        textComponent.updateUI();
    }
    public void transitionMainMenuToBackstory(){
        mainframe.hideMenuScreen(); //allocate this to translator
        mainframe.showBackstoryScreenNameEntry(); //allocate to translator
    }
    public void editButtonText(JButton button, String text){
        button.setText(text);
        button.updateUI();
        System.out.println("SETTING TEXT " + text);
    }

    public void addEventListener(JComponent comp){

    }

}