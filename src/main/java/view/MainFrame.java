package view;

import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MainFrame extends JFrame {

    public JButton playButton, exitButton, loadButton, helpButton, backstoryOptionOneButton, backstoryOptionTwoButton, backstoryOptionThreeButton;
    public JPanel titlePanel, menuPanel, gameIntroPanel, backstoryTextPanel, backstoryOptionsPanel;
    public JTextArea textArea = new JTextArea(20, 20);;
    public JTextArea backstoryTextArea = new JTextArea(20,20);
    public JLabel bannerLabel;


    private Container con;

    public MainFrame() {
        setFrameConfigs();
        con = getContentPane();

        setAllButtons();
        setAllPanels();
        textArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 4));

        ImageIcon banner = new ImageIcon("resources/GetRichBanner.png");
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
        con.add(backstoryTextPanel);
        backstoryOptionsPanel.add(backstoryOptionOneButton);
        backstoryOptionsPanel.add(backstoryOptionTwoButton);
        backstoryOptionsPanel.add(backstoryOptionThreeButton);
        con.add(backstoryOptionsPanel);





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
        exitButton = createJButton("Exit Game", 150, 50, false, Color.white, Color.green);
        loadButton = createJButton("Load Game", 150, 20, false, Color.white, Color.red);
        helpButton = createJButton("Help Menu", 150, 20, false, Color.white, Color.blue);

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
}