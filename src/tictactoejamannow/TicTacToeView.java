package tictactoejamannow;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class TicTacToeView extends JFrame {
    
    private JLabel statLabel;
    private JButton[] buttons = new JButton[9];
    private JButton backButton;
    private JButton restartButton;
    
    public TicTacToeView() {
        initUI();    
    }
 
    private void initUI() {
        //window properties
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 530);
        setLocationRelativeTo(null);
        
        //statLabel properties
        statLabel = new JLabel();
        statLabel.setOpaque(true);
        statLabel.setBackground(Color.yellow);
        statLabel.setSize(350, 50);
        statLabel.setLocation(50, 0);
        statLabel.setFont(new Font("Arial", Font.BOLD, 24));
        statLabel.setHorizontalAlignment(JLabel.CENTER);
        String statText = "TEST LABEL";
        statLabel.setText(statText);
        add(statLabel);
        
        //backButton
        backButton = new JButton();
        backButton.setSize(50, 50);
        backButton.setLocation(0, 0);
        backButton.setText("<");
        backButton.setFocusPainted(false);
        add(backButton);
        
        //restartButton
        restartButton = new JButton();
        restartButton.setSize(50, 50);
        restartButton.setLocation(400, 0);
        restartButton.setText("O");
        restartButton.setFocusPainted(false);
        add(restartButton); 
        
        //buttons properties
        for (int i = 0; i < 9; i++) {
            JButton btn = new JButton();
            btn.setSize(150, 150);
            int r = i/3;
            int c = i%3;
            btn.setLocation(c*150, r*150+50);
            btn.setFocusPainted(false);
            btn.setFont(new Font("Arial", Font.BOLD, 56));
            add(btn);
            buttons[i] = btn;
        }
    }
    
    public void resetButtons() {
        SwingUtilities.invokeLater(() -> {
            for (int i = 0; i < 9; i++) {
                buttons[i].setText("");
                buttons[i].setBackground(null);
                buttons[i].setForeground(null);
            }
        });
    }
    
    public void addClickListenersToButtons(ActionListener[] listeners) {
        for (int i = 0; i < 9; i++)
            buttons[i].addActionListener(listeners[i]);
    }
    
    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }
    
    public void hideRestartButton() {
        SwingUtilities.invokeLater(() -> {
            restartButton.setVisible(false);
        });
    }
    
    public void addRestartButtonListener(ActionListener listener) {
        restartButton.addActionListener(listener);
    }
    
    public void setCircleMarkAt(int r, int c) {
        int pos = r*3+c;
        setCircleMarkAt(pos);
    }
    
    public void setCrossMarkAt(int r, int c) {
        int pos = r*3+c;
        setCrossMarkAt(pos);
    }
    
    public void setCircleMarkAt(int pos) {
        SwingUtilities.invokeLater(() -> {
            buttons[pos].setText("O");
            buttons[pos].setForeground(Color.RED);    
        });
    }
    
    public void setCrossMarkAt(int pos) {
        SwingUtilities.invokeLater(() -> {
            buttons[pos].setText("X");
            buttons[pos].setForeground(Color.BLUE);    
        });
    }
    
    public void setStatText(String text) {
        SwingUtilities.invokeLater(() -> {
            statLabel.setText(text);    
        });
    }
    
    public void markWinButtons(int[] winSlots) {
        SwingUtilities.invokeLater(() -> {
            for (int i = 0; i < winSlots.length; i++) {
                buttons[winSlots[i]].setBackground(Color.GREEN);
            }
        });
    }

    @Override
    public void setVisible(boolean bln) {
        SwingUtilities.invokeLater(() -> {
            super.setVisible(bln);    
        });
    }
    
    
}
