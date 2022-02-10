package view;

import models.Person;
import models.Scene;
import models.SceneContainer;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Scanner;

public class MainFrame extends JFrame {

    Scanner scanner = new Scanner(System.in);

    public JButton playButton, exitButton, loadButton, helpButton, backstoryOptionOneButton, backstoryOptionTwoButton, backstoryOptionThreeButton,skipBackstory;
    public JPanel titlePanel, menuPanel, gameIntroPanel, backstoryTextPanel, backstoryOptionsPanel, attributesPanel, playerInfoPanel, sceneInfo, healthPanel, wealthPanel, optionsPanel, sceneArt;
    public JTextArea textArea = new JTextArea(20, 20);;
    public JTextArea backstoryTextArea = new JTextArea(20,20), playerInfoTextArea = new JTextArea(20,20), sceneInfoTextArea = new JTextArea(20,20), optionsTextArea = new JTextArea(5,20);
    public JLabel bannerLabel;
    public JLabel attributesLabel = new JLabel(), healthLabel = new JLabel(), wealthLabel = new JLabel();
    //    public JLabel attributesLabel1 = new JLabel(), attributesLabel2 = new JLabel(), attributesLabel3 = new JLabel();

    private Container con;

    public MainFrame() {
        setFrameConfigs();
        con = getContentPane();

        setAllButtons();
        setAllPanels();
        textArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 4));

        ImageIcon banner = new ImageIcon(new ImageIcon("resources/GetRichBanner.png").getImage().getScaledInstance(500,150,Image.SCALE_AREA_AVERAGING));
        bannerLabel = new JLabel();
        //used for main menu
        bannerLabel.setIcon(banner);
        titlePanel.add(bannerLabel);
        gameIntroPanel.add(textArea);
        menuPanel.add(playButton);
        menuPanel.add(exitButton);
        menuPanel.add(loadButton);
        menuPanel.add(helpButton);
        // testing player attributes
        menuPanel.add(skipBackstory);
        //add tp content pane
        con.add(menuPanel);
        con.add(titlePanel);
        con.add(gameIntroPanel);

        // -- Backstory components --
        con.add(backstoryTextPanel);
        backstoryOptionsPanel.add(backstoryOptionOneButton);
        backstoryOptionsPanel.add(backstoryOptionTwoButton);
        backstoryOptionsPanel.add(backstoryOptionThreeButton);
        con.add(backstoryOptionsPanel);


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
        getContentPane().setBackground(Color.blue);
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
        //1
        backstoryOptionOneButton = createJButton("1", 150, 50, false, Color.WHITE, Color.red);
        backstoryOptionOneButton.setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(5, 15, 5, 15)));
        //2
        backstoryOptionTwoButton = createJButton("2", 150, 50, false, Color.white, Color.green);
        //3
        backstoryOptionThreeButton = createJButton("2", 150, 50, false, Color.white, Color.blue);
    }

    private void setAllPanels() {
        //main menu
        titlePanel = createJPanel(200, 50, 500, 150, Color.darkGray, true);
        gameIntroPanel= createJPanel(225, 300, 450, 100, Color.yellow, true);
        menuPanel = createJPanel(350, 450, 225, 225, Color.yellow, true);
        //backstory
        backstoryTextPanel = createJPanel(100, 100, 275, 400, Color.gray, false);
        backstoryOptionsPanel = createJPanel(550, 100, 275, 400, Color.gray, false);
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

    public void writeToTextArea(JTextArea textArea, String string) {
        textArea.setFont(new Font("Arial", Font.BOLD, 11));
        textArea.setPreferredSize(new Dimension(425, 75));
        textArea.setBackground(Color.white);
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

    public void showBackstoryScreen() {
        backstoryTextPanel.setVisible(true);
        backstoryOptionsPanel.setVisible(true);
    }

    public void showAttributesScreen(Person player){
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
                + "\nCareer Level: " + player.getCareer());
        healthLabel.setText("health: " + player.getHealthPoints());
        wealthLabel.setText("Net worth: " + player.getNetWorth());
        attributesPanel.setVisible(true);
        playerInfoPanel.setVisible(true);
        healthPanel.setVisible(true);
        wealthPanel.setVisible(true);
        sceneInfo.setVisible(true);
        optionsPanel.setVisible(true);
        letsPlay(player);
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