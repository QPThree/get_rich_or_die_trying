package view;
import javax.swing.*;
import java.awt.*;

public class Draw extends JPanel{

    public void drawing(){
        repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.yellow);
        g.fillOval(10,100,100,100);
        System.out.println("Calling paint component");
    }
}
