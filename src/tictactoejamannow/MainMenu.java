package tictactoejamannow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainMenu extends JFrame {
    
    private JLabel titleLabel1;
    private JLabel titleLabel2;
    private JButton singleButton;
    private JButton multiLocalButton;
    private JButton multiInterlocalButton;
    
    public MainMenu() {
        super("[Main Menu] Tic Tac Toe Jaman Now");
        initUI();
    }
    
    private void initUI() {
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        singleButton = new JButton("Singleplayer (Versus AI)");
        singleButton.setPreferredSize(new Dimension(250, 50));
        add(singleButton, BorderLayout.NORTH);
        singleButton.addActionListener((e) -> {
            
        });
        
        multiLocalButton = new JButton("Multiplayer (in one computer)");
        multiLocalButton.setPreferredSize(new Dimension(250, 50));
        add(multiLocalButton, BorderLayout.CENTER);
        multiLocalButton.addActionListener((e) -> {
            
        });
        
        multiInterlocalButton = new JButton("Multiplayer (through the internet)");
        multiInterlocalButton.setPreferredSize(new Dimension(250, 50));
        add(multiInterlocalButton, BorderLayout.SOUTH);
        multiInterlocalButton.addActionListener((e) -> {
            
        });
        
        pack();
        setLocationRelativeTo(null);
    }
}
