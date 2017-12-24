package tictactoejamannow;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class SplashScreen extends JFrame {
    
    private JLabel label1;
    private JLabel label2;
    
    public SplashScreen() {
        initUI();
    }
    
    private void initUI() {
        Container pane = getContentPane();
        setLayout(null);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pane.setBackground(Color.LIGHT_GRAY);
        setSize(300, 150);
        setLocationRelativeTo(null);
        
        label1 = new JLabel("TIC TAC TOE");
        label1.setFont(new Font("Arial", Font.BOLD, 24));
        label1.setLocation(72, 40);
        label1.setSize(200, 30);
        label1.setForeground(Color.WHITE);
        add(label1);
        
        label2 = new JLabel("JAMAN NOW");
        label2.setFont(new Font("Arial", Font.BOLD, 24));
        label2.setLocation(73, 70);
        label2.setSize(200, 30);
        label2.setForeground(Color.WHITE);
        add(label2);
    }
    
    public void showUp() {
        setVisible(true); 
        Thread thread = new Thread(() -> {
            while (!isVisible());           
            Tool.sleep(2000);
            setVisible(false);
            dispose();
            showMainMenu();
        });
        thread.start();
    }
    
    private void showMainMenu() {
        MainMenu.getInstance().setVisible(true);
    }
}
