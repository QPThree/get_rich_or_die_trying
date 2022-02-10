package view;

import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    public JButton playButton, exitButton, loadButton, helpButton, backstoryOptionOneButton, backstoryOptionTwoButton, backstoryOptionThreeButton, continueButton;
    public JPanel titlePanel, menuPanel, gameIntroPanel, backstoryTextPanel, backstoryOptionsPanel, backstoryTitlePanel;
    public JTextArea textArea = new JTextArea(20, 20);;
    public TextField nameTextField = new TextField("Enter Name", 12);
    public JTextArea backstoryTextArea = new JTextArea(10,10);
    public JLabel bannerLabel, backstoryLabel;
    public ArrayList<JButton> allBackstoryOptionsButtons = new ArrayList<>();


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

        con.add(backstoryOptionsPanel);
        con.add(backstoryTextPanel);
        con.add(backstoryTitlePanel);





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

    }

    private void setAllPanels() {
        //main menu
        titlePanel = createJPanel(200, 50, 500, 150, Color.darkGray, true);
        gameIntroPanel= createJPanel(225, 300, 450, 100, Color.yellow, true);
        menuPanel = createJPanel(350, 450, 225, 225, Color.yellow, true);
        //backstory
        backstoryTextPanel = createJPanel(100, 150, 275, 400, Color.white, false);
        backstoryTextPanel.setBorder(new LineBorder(Color.black, 2));
        backstoryOptionsPanel = createJPanel(550, 150, 275, 400, Color.white, false);
        backstoryOptionsPanel.setLayout(null);
        backstoryOptionsPanel.setBorder(new LineBorder(Color.black, 2));
        backstoryTitlePanel = createJPanel(185, 25, 500, 100, Color.white, false);
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
        System.out.println(string);

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
        backstoryOptionsPanel.updateUI();
    }

    public void showContinueButton() {
        continueButton.setVisible(true);
        continueButton.updateUI();
        backstoryOptionsPanel.updateUI();
        System.out.println("SHOULD SEE CONTINUE BUTTON");
    }
}