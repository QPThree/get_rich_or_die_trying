package view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public JButton playButton, exitButton, loadButton, helpButton;
    public JPanel titlePanel, menuPanel, gameIntroPanel;
    private JTextArea textArea = new JTextArea(20, 20);
    private JTextArea userRewardsText = new JTextArea();
    public JLabel bannerLabel;

    private Container con;

    public MainFrame() {
        setFrameConfigs();
        con = getContentPane();

        setAllButtons();
        setAllPanels();
        textArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 4));

        ImageIcon banner = new ImageIcon(new ImageIcon("resources/GetRichBanner.png").getImage().getScaledInstance(500,150,Image.SCALE_AREA_AVERAGING));
        bannerLabel = new JLabel();
        bannerLabel.setIcon(banner);
        titlePanel.add(bannerLabel);
        gameIntroPanel.add(textArea);
        menuPanel.add(playButton);
        menuPanel.add(exitButton);
        menuPanel.add(loadButton);
        menuPanel.add(helpButton);

        con.add(menuPanel);
        con.add(titlePanel);
        con.add(gameIntroPanel);
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
        playButton = createJButton("Play Game", 150, 50, false, Color.WHITE, Color.GREEN);
        exitButton = createJButton("Exit Game", 150, 50, false, Color.white, Color.red);
        loadButton = createJButton("Load Game", 150, 20, false, Color.white, Color.gray);
        helpButton = createJButton("Help Menu", 150, 20, false, Color.white, Color.blue);
    }

    private void setAllPanels() {
        titlePanel = createJPanel(200, 50, 500, 150, Color.darkGray, true);
        gameIntroPanel= createJPanel(225, 300, 450, 100, Color.yellow, true);
        menuPanel = createJPanel(350, 450, 225, 225, Color.yellow, true);
    }

    public void clearMenuPanel() {
        con.remove(menuPanel);
    }

    public void writeToTextArea(String string) {
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
}