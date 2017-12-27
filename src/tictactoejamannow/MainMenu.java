package tictactoejamannow;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Arrays;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

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
            JRadioButton[] radioButtons = showLanNetworkOption();
            if (radioButtons[0].isSelected()) { //hostRadioBtn selected
                new TicTacToeMultiIntraLocalServerController().startGame();
            } else if (radioButtons[1].isSelected()) { //joinRadioBtn selected
                new TicTacToeMultiIntraLocalClientController().startGame();
            }
        });
        
        pack();
        setLocationRelativeTo(null);
    }
    
    private static JRadioButton[] showLanNetworkOption() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel msg = new JLabel("Choose an option");
        JRadioButton hostRadioBtn = new JRadioButton("Host a game");
        JRadioButton joinRadioBtn = new JRadioButton("Join a game");
        ButtonGroup radioBtnGroup = new ButtonGroup();
        radioBtnGroup.add(hostRadioBtn);
        radioBtnGroup.add(joinRadioBtn);
        panel.add(msg, BorderLayout.NORTH);
        panel.add(hostRadioBtn, BorderLayout.CENTER);
        panel.add(joinRadioBtn, BorderLayout.SOUTH);
        JOptionPane.showMessageDialog(
                null, 
                panel, 
                "Choose an option", 
                JOptionPane.QUESTION_MESSAGE
        );
        return new JRadioButton[] {hostRadioBtn, joinRadioBtn};
    }
}
