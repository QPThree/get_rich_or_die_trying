package view;

import models.Person;
import models.Scene;
import models.SceneContainer;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

import java.util.ArrayList;
import java.util.Scanner;
public class MainFrame extends JFrame {
    Scanner scanner = new Scanner(System.in);
    public JButton playButton, exitButton, loadButton, helpButton, backstoryOptionOneButton, backstoryOptionTwoButton, backstoryOptionThreeButton, continueButton, optionA, optionB,skipBackstory;
    public JPanel titlePanel, menuPanel, gameIntroPanel, backstoryTextPanel, backstoryOptionsPanel, backstoryTitlePanel, attributesPanel, playerInfoPanel, sceneInfo, healthPanel, wealthPanel, optionsPanel, sceneArt, oneOfTwoOptionsPanel;
    public JTextArea textArea = new JTextArea(20, 20), playerInfoTextArea = new JTextArea(20,20), sceneInfoTextArea = new JTextArea(20,20), optionsTextArea = new JTextArea(5,20);
    public TextField nameTextField = new TextField("Enter Name", 12);
    public JTextArea backstoryTextArea = new JTextArea(10,10);
    public JLabel bannerLabel, backstoryLabel;
    public JLabel attributesLabel = new JLabel(), healthLabel = new JLabel(), wealthLabel = new JLabel();
    public ArrayList<JButton> allBackstoryOptionsButtons = new ArrayList<>();


    private Container con;

    public MainFrame() {
        setFrameConfigs();
        con = getContentPane();

        setAllButtons();
        setAllPanels();
        textArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 6));

        ImageIcon banner = new ImageIcon(new ImageIcon("resources/GetRichBanner.png").getImage().getScaledInstance(500,150,Image.SCALE_AREA_AVERAGING));
        //ImageIcon banner = new ImageIcon(new ImageIcon("resources/GetRichDieTrying.jpg").getImage().getScaledInstance(500,150,Image.SCALE_AREA_AVERAGING));
        bannerLabel = new JLabel();
        //used for main menu
        bannerLabel.setIcon(banner);
        titlePanel.add(bannerLabel);
        gameIntroPanel.add(textArea);
        menuPanel.add(playButton);
        menuPanel.add(loadButton);
        menuPanel.add(exitButton);
        menuPanel.add(helpButton);

        // testing player attributes
        menuPanel.add(skipBackstory);

        //add tp content pane
        con.add(menuPanel);
        con.add(titlePanel);
        con.add(gameIntroPanel);

        // -- Backstory components --
        ImageIcon backstoryBanner = new ImageIcon(new ImageIcon("resources/Background.png").getImage().getScaledInstance(500,200,Image.SCALE_AREA_AVERAGING));
        backstoryLabel = new JLabel();
        backstoryLabel.setIcon(backstoryBanner);
        backstoryTitlePanel.add(backstoryLabel);
        backstoryTitlePanel.setBackground(Color.white);
        backstoryTextPanel.setBounds(150,150, 400,400);
        backstoryTextPanel.add(backstoryTextArea);


        backstoryOptionsPanel.add(nameTextField);
        backstoryOptionsPanel.add(continueButton);


        oneOfTwoOptionsPanel.add(optionA);
        oneOfTwoOptionsPanel.add(optionB);
        con.add(oneOfTwoOptionsPanel);



        con.add(backstoryOptionsPanel);
        con.add(backstoryTextPanel);
        con.add(backstoryTitlePanel);


        // -- Attributes components --
        attributesPanel.add(attributesLabel);
        con.add(attributesPanel);
        playerInfoPanel.add(playerInfoTextArea);
        con.add(playerInfoPanel);

        healthPanel.add(healthLabel);
        con.add(healthPanel);
        wealthPanel.add(wealthLabel);
        con.add(wealthPanel);

        optionsPanel.add(optionsTextArea);
        con.add(optionsPanel);

        sceneInfo.add(sceneInfoTextArea);

//        Draw shape = new Draw();
//        shape.drawing();
//        shape.setVisible(true);
//        scene.add(shape);
        con.add(sceneInfo);
//        scene.add(new CustomPaintComponent());

        setVisible(true);
    }

    private void setFrameConfigs() {

        setSize(900, 700);
        setResizable(false); // enable the resize of the frame
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.darkGray);
        setLayout(null);
        setVisible(true);

    }

    private void setAllButtons() {
        //main menu

        playButton = createJButton("Play Game", 150, 50, false, Color.WHITE, Color.GREEN);
        exitButton = createJButton("Exit Game", 150, 50, false, Color.white, Color.red);
        loadButton = createJButton("Load Game", 150, 20, false, Color.white, Color.gray);
        helpButton = createJButton("Help Menu", 150, 20, false, Color.white, Color.blue);
        // Testing player attributes
        skipBackstory = createJButton("skip Backstory",150,20,false, Color.black, Color.red);


        //backstory
        continueButton = createJButton("Continue", 150, 50, false, Color.green, Color.white);
        continueButton.setBounds(70,275, 150, 50 );
        continueButton.setBorder(new LineBorder(Color.lightGray, 1));

        //1
        backstoryOptionOneButton = createJButton("1", 150, 50, false, Color.black, Color.white);
        backstoryOptionOneButton.setBounds(70, 75, 150, 50); //set bounds moves the button to desired X/Y coordinate
        backstoryOptionOneButton.setBorder(new LineBorder(Color.lightGray, 1));
        backstoryOptionOneButton.setOpaque(true); //needed for mac to see color

        //2
        backstoryOptionTwoButton = createJButton("2", 150, 50, false, Color.black, Color.white);
        backstoryOptionTwoButton.setBounds(70, 175, 150, 50);
        backstoryOptionTwoButton.setBorder(new LineBorder(Color.lightGray, 1));

        //3
        backstoryOptionThreeButton = createJButton("3", 150, 50, false, Color.black, Color.white);
        backstoryOptionThreeButton.setBounds(70,275, 150, 50 );
        backstoryOptionThreeButton.setBorder(new LineBorder(Color.lightGray, 1));

         allBackstoryOptionsButtons.add(backstoryOptionOneButton);
         allBackstoryOptionsButtons.add(backstoryOptionTwoButton);
         allBackstoryOptionsButtons.add(backstoryOptionThreeButton);


         //Buttons where its Yes or No
        optionA = createJButton("A", 150, 50, false, Color.black, Color.GREEN);
        optionA.setBounds(70, 275, 150, 50);
        optionA.setBorder(new LineBorder(Color.lightGray, 1));
        optionB = createJButton("B", 150, 50, false, Color.black, Color.red);
        optionB.setBounds(70, 375, 150, 50);
        optionB.setBorder(new LineBorder(Color.lightGray, 1));


    }

    private void setAllPanels() {
        //main menu
        titlePanel = createJPanel(200, 50, 500, 150, Color.darkGray, true);
        gameIntroPanel= createJPanel(225, 300, 450, 150, Color.darkGray, true);
        menuPanel = createJPanel(350, 500, 125, 250, Color.darkGray, true);
        //backstory

        backstoryTextPanel = createJPanel(100, 100, 275, 400, Color.gray, false);
        backstoryTextPanel.setBorder(new LineBorder(Color.black, 2));
        backstoryOptionsPanel = createJPanel(550, 100, 275, 400, Color.white, false);
        backstoryOptionsPanel.setLayout(null);
        backstoryOptionsPanel.setBorder(new LineBorder(Color.black, 2));
        backstoryTitlePanel = createJPanel(185, 25, 500, 100, Color.white, false);

        //One of two options panel(panel to hold two buttons)
        oneOfTwoOptionsPanel = createJPanel(550, 100, 275, 400, Color.white, false);
      
        // Attributes menu
        attributesPanel = createJPanel(500,10,350, 40, Color.white, false);
        playerInfoPanel = createJPanel(50,10,350, 60, Color.white, false);
        sceneInfo = createJPanel(100,200,300,300,Color.white,false);
        healthPanel = createJPanel(50,550,150,40, Color.white,false);
        wealthPanel = createJPanel(600,550,150,40, Color.white,false);
        optionsPanel = createJPanel(550,200,300,50,Color.white,false);
    }

    public void clearMenuPanel() {
        con.remove(menuPanel);
    }


    public void writeToTextArea(JTextArea textArea,String string) {
        //textArea.setPreferredSize(new Dimension(425, 75));
        textArea.removeAll();
        writeToTextArea(textArea, Color.white, string);

    }
    public void writeToTextArea(JTextArea textArea, Color color, String string) {
        textArea.setFont(new Font("Arial", Font.BOLD, 11));
        textArea.setBackground(color);
        textArea.setText(string);
    }
    private JButton createJButton(String title, int width, int height, boolean focusable, Color foreground, Color background) {
        JButton product = new JButton(title);
        product.setPreferredSize(new Dimension(width, height));
        product.setFocusable(focusable);
        product.setForeground(foreground);
        product.setBackground(background);
        return product;
    }

    private JPanel createJPanel(int x, int y, int width, int height, Color background, boolean visible) {
        JPanel product = new JPanel();
        product.setBounds(x, y, width, height);
        product.setBackground(background);
        product.setVisible(visible);
        return product;
    }

    public void hideMenuScreen(){
        menuPanel.setVisible(false);
        titlePanel.setVisible(false);
        gameIntroPanel.setVisible(false);
        menuPanel.updateUI();
    }

    public void showBackstoryScreenNameEntry() {
        nameTextField.setFont(new Font("Arial", Font.BOLD, 15));
        nameTextField.setForeground(Color.red);
        nameTextField.setBackground(Color.white);
        nameTextField.setBounds(70, 175, 150, 50);
        backstoryTextPanel.setVisible(true);
        backstoryOptionsPanel.setVisible(true);
        backstoryTitlePanel.setVisible(true);
        backstoryTextPanel.updateUI();
        con.setBackground(Color.white);
    }

    public void hideBackstoryTextPanel(){
        backstoryTextPanel.setVisible(false);
        backstoryTitlePanel.setVisible(false);
        backstoryOptionsPanel.removeAll();
    }

    public void showBackstoryOptions(){
        backstoryOptionsPanel.remove(nameTextField);
        continueButton.setVisible(false);

        backstoryOptionsPanel.add(backstoryOptionOneButton);
        backstoryOptionsPanel.add(backstoryOptionTwoButton);
        backstoryOptionsPanel.add(backstoryOptionThreeButton);
        backstoryOptionsPanel.updateUI();
    }

    public void hideBackstorySelectionScreen() {
       backstoryOptionOneButton.setVisible(false);
       backstoryOptionTwoButton.setVisible(false);
       backstoryOptionThreeButton.setVisible(false);

       backstoryOptionsPanel.updateUI();
    }

    public void showBackstorySelectionScreen(){
        backstoryOptionOneButton.setVisible(true);
        backstoryOptionThreeButton.setVisible(true);
        backstoryOptionsPanel.setVisible(true);
        backstoryOptionsPanel.updateUI();
    }

    public void showContinueButton() {
        continueButton.setVisible(true);
        continueButton.updateUI();
        backstoryOptionsPanel.updateUI();
    }

    public void hideContinueButton() {
        continueButton.setVisible(false);
        continueButton.updateUI();
        backstoryOptionsPanel.updateUI();
    }

    public void showTwoOptionsScreen(){

        oneOfTwoOptionsPanel.setVisible(true);
        backstoryOptionsPanel.setVisible(false);
        optionA.setVisible(true);
        optionB.setVisible(true);

    }

    public void hideTwoOptionsScreen(){
        oneOfTwoOptionsPanel.setVisible(false);
        backstoryOptionsPanel.setVisible(false);
        optionA.setVisible(false);
        optionB.setVisible(false);
    }

    public void showAttributesScreen(Person player){
        hideBackstorySelectionScreen();
        SceneContainer scenes = new SceneContainer();
        Scene currentScene = scenes.getRandomScene(player);
//        sceneInfoTextArea.setText(currentScene.getArt());
        sceneInfoTextArea.setWrapStyleWord(true);
        sceneInfoTextArea.setLineWrap(true);
//        sceneInfoTextArea.setText(currentScene.getPrompt());
//        optionsTextArea.setText(getOptions(currentScene));
        attributesLabel.setText("Strength: " + player.getStrength() +
        " Intellect: " + player.getIntellect() + " Creativity: " + player.getCreativity());
        playerInfoTextArea.setText("Name: " + player.getName() +
                "\nAge: " + player.getAge()
                + "\nCareer Choice: " + player.getCareer());
        healthLabel.setText("Health: " + player.getHealthPoints());
        wealthLabel.setText("Net worth: " + player.getPrettyNetWorth());
        attributesPanel.setVisible(true);
        playerInfoPanel.setVisible(true);
        healthPanel.setVisible(true);
        wealthPanel.setVisible(true);
        sceneInfo.setVisible(true);
        continueButton.setVisible(true);
        optionsPanel.add(continueButton);
        optionsPanel.setVisible(true);
//        letsPlay(player);
    }


    private String getOptions(Scene currentScene){
        String optionz = "";
        for (String option : currentScene.getOptions())
            optionz += option + "\n";
        return optionz;
    }

    private void letsPlay(Person player){
        SceneContainer scenes = new SceneContainer();
//        while (true) {
            Scene currentScene = scenes.getRandomScene(player);
            sceneInfoTextArea.setText(currentScene.getPrompt());
            System.out.println(currentScene.getArt());
            System.out.println("\n+++++++ 5 years later +++++++");
            player.addAge(5);
//            int input = prompt(currentScene);
            optionsTextArea.setText(getOptions(currentScene));
            playerInfoTextArea.setText("Name: " + player.getName() +
                    "\nAge: " + player.getAge()
                    + "\nCareer Level: " + player.getCareer());
        healthLabel.setText("health: " + player.getHealthPoints());
        wealthLabel.setText("Net worth: " + player.getNetWorth());
//            String userChoice = userChoice();
//            clearScreen();
//            displayOutcome(input, currentScene);
//            runEffect(input, currentScene);
//            String salaryReport = player.addSalary();
//            System.out.println("\nEnter any key to see your 5-year summary");
//            getInput();
//            displaySceneSummary(salaryReport);
//            nextTurnPrompt();

//        }
    }

    private String userChoice(){
        String userInput = scanner.nextLine().trim().toLowerCase();
        scanner.close();
        return userInput;
    }

}