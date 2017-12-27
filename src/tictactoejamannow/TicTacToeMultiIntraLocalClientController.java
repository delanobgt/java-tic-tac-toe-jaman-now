package tictactoejamannow;

import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class TicTacToeMultiIntraLocalClientController {
    private final TicTacToeView view = new TicTacToeView();
    private TicTacToeModel model = new TicTacToeModel();
    private final Client client = new Client();
    private volatile boolean opponentThinking = false;
    private String[] names;
    
    public TicTacToeMultiIntraLocalClientController() {
        initBackButtonListener();
        initRestartButtonListener();
        initButtonClickListener();
    }
    
    public final void startGame() {
        String playerName = JOptionPane.showInputDialog(
                                        null, 
                                        "Player Name:", 
                                        "Input Name", 
                                        JOptionPane.PLAIN_MESSAGE);
        if (playerName == null || playerName.length() == 0) return;
        
        String address = JOptionPane.showInputDialog(
                null, 
                "Server Address: ",
                "Input Server Address",
                JOptionPane.OK_CANCEL_OPTION
        );
        if (address == null || address.length() == 0) return;
        MainMenu.getInstance().setVisible(false);
        
        String[] split = address.split(":");
        System.out.println(Arrays.toString(split));
        String ip = split[0];
        int port = Integer.parseInt(split[1]);
        if (client.connect(ip, port)) {
            client.sendObject(playerName);
            names = new String[] {(String)client.readObject(), playerName};
            updateTurnText();
            SwingUtilities.invokeLater(() -> view.setVisible(true));
            opponentPlays();
        } else {
            MainMenu.getInstance().setVisible(true);
        }
    }
    
    private void updateTurnText() {
        view.setStatText(names[model.getTurn()]+"'s turn");
    }
    
    private void initBackButtonListener() {
        view.addBackButtonListener((e) -> {
            view.dispose();
            MainMenu.getInstance().setVisible(true);
        });
    }
    
    private void initRestartButtonListener() {
        view.addRestartButtonListener((e) -> {
            model = new TicTacToeModel();
            view.resetButtons();
            updateTurnText();
        });
    }
    
    private void initButtonClickListener() {
        ActionListener[] listeners = new ActionListener[9];
        for (int i = 0; i < 9; i++) {
            final int pos = i;
            listeners[i] = (e) -> {
                if (opponentThinking) return;
                if (model.checkWin() != model.UNKNOWN_YET ||
                        model.checkDraw() ||
                        !model.isEmptyAt(pos)) return;
                
                model.setMarkAt(pos);
                view.setCrossMarkAt(pos);
                updateTurnText();
                client.sendObject(pos);
                
                int status;
                if ((status = model.checkWin()) != model.UNKNOWN_YET) {
                    view.setStatText(names[status]+" won!!");
                    view.markWinButtons(model.getWinSlots());
                } else if (model.checkDraw()) 
                    view.setStatText("It's a tie");
                
                opponentPlays();
            };
        }
        view.addClickListenersToButtons(listeners);
    }
    
    private void opponentPlays() {
        opponentThinking = true;
        int pos = (Integer)client.readObject();
        opponentThinking = false;
        if (model.checkWin() != model.UNKNOWN_YET ||
                model.checkDraw() ||
                !model.isEmptyAt(pos)) return;

        model.setMarkAt(pos);
        view.setCircleMarkAt(pos);
        updateTurnText();        

        int status;
        if ((status = model.checkWin()) != model.UNKNOWN_YET) {
            view.setStatText(names[status]+" won!!");
            view.markWinButtons(model.getWinSlots());
        } else if (model.checkDraw()) 
            view.setStatText("It's a tie");
    }
}
