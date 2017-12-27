package tictactoejamannow;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainMenu extends JFrame {
    
    private static MainMenu instance = null;
    
    private JLabel titleLabel1;
    private JLabel titleLabel2;
    private JButton singleButton;
    private JButton multiLocalButton;
    private JButton multiInterlocalButton;
    
    private MainMenu() {
        super("[Main Menu] Tic Tac Toe Jaman Now");
        initUI();
    }
    
    public static MainMenu getInstance() {
        if (instance == null) instance = new MainMenu();
        return instance;
    }
    
    private void initUI() {
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        singleButton = new JButton("Singleplayer (Versus AI)");
        singleButton.setPreferredSize(new Dimension(250, 50));
        add(singleButton, BorderLayout.NORTH);
        singleButton.addActionListener((e) -> {
            new TicTacToeSingleController().startGame();
        });
        
        multiLocalButton = new JButton("Multiplayer (in one computer)");
        multiLocalButton.setPreferredSize(new Dimension(250, 50));
        add(multiLocalButton, BorderLayout.CENTER);
        multiLocalButton.addActionListener((e) -> {
            new TicTacToeMultiLocalController().startGame();
        });
        
        multiInterlocalButton = new JButton("Multiplayer (using LAN)");
        multiInterlocalButton.setPreferredSize(new Dimension(250, 50));
        add(multiInterlocalButton, BorderLayout.SOUTH);
        multiInterlocalButton.addActionListener((e) -> {
            new TicTacToeMultiIntraLocalController().startGame();
        });
        
        pack();
        setLocationRelativeTo(null);
    }
}
