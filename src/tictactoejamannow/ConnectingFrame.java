package tictactoejamannow;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

class ConnectingFrame extends JFrame {
    
    private JLabel msgLabel;
    private JButton btn;
    
    public ConnectingFrame() {
        setUndecorated(true);
        setMinimumSize(new Dimension(300, 160));
        setLayout(new BorderLayout());
        msgLabel = new JLabel();
        add(msgLabel, BorderLayout.NORTH);
        btn = new JButton("Cancel");
        add(btn, BorderLayout.SOUTH);
        pack();
        
        setLocationRelativeTo(null);
    }
    
    public JLabel getLabel() {
        return msgLabel;
    }
    
    public JButton getButton() {
        return btn;
    }
    
    public void setTextProperties(String ip, int port) {
        msgLabel.setText(String.format(
                "<html>" +
                    "<pre>" + "<br/>" +
                        "  Server Address:   " + "<br/>" + "<br/>" +
                        "        %s:%d   " + "<br/>" + "<br/>" +
                        "  Waiting for connection..  " + "<br/>" +
                    "</pre>" +
                "</html>", 
                    ip,
                    port
            ));
    }
}
