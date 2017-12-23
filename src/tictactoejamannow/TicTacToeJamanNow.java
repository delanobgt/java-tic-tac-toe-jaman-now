package tictactoejamannow;

import javax.swing.SwingUtilities;

public class TicTacToeJamanNow {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SplashScreen().showUp();
        });
    }
    
}
