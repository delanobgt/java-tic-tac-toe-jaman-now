package tictactoejamannow;

import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class TicTacToeMultiIntraLocalServerController {
    private TicTacToeView view = new TicTacToeView();
    private TicTacToeModel model = new TicTacToeModel();
    private final Server server = new Server(9999);
    private ConnectingFrame connFrame;
    private volatile boolean opponentThinking = false;
    private String[] names;
    
    public TicTacToeMultiIntraLocalServerController() {
        initBackButtonListener();
        view.hideRestartButton();
        initButtonClickListener();
    }
    
    public final void startGame() {
        String playerName = JOptionPane.showInputDialog(
                                        null, 
                                        "Player Name:", 
                                        "Input Name", 
                                        JOptionPane.PLAIN_MESSAGE);
        if (playerName == null || playerName.length() == 0) return;
        MainMenu.getInstance().setVisible(false);
        
        SwingUtilities.invokeLater(() -> {
            connFrame = new ConnectingFrame();
            connFrame.setTextProperties(
                    server.getInetAddress().getHostAddress(),
                    server.getPort()
            );
            connFrame.setVisible(true);
            connFrame.getButton().addActionListener((e) -> {
                if (connFrame.getButton().getText().equals("Cancel")) {
                    server.close();
                    connFrame.setVisible(false);
                    MainMenu.getInstance().setVisible(true);
                } else if (connFrame.getButton().getText().equals("Okay")) {
                    connFrame.setVisible(false);
                    updateTurnText();
                    view.setVisible(true);    
                }
            });
        });
        
        Thread thread = new Thread(() -> {
            System.out.println("start listening");
            if(server.waitForConnection()){ //connection success
                System.out.println("accepted");
                server.sendObject(playerName);
                names = new String[] {playerName, (String)server.readObject()};
                SwingUtilities.invokeLater(() -> {
                    connFrame.getLabel().setText("Connected to "+names[1]);
                    connFrame.getButton().setText("Okay");    
                });
            } else {                        //connection failure
                System.out.println("failure");
                if (connFrame != null)
                    connFrame.setVisible(false);
                MainMenu.getInstance().setVisible(true);
            }
        });
        thread.start();
    }
    
    private void updateTurnText() {
        view.setStatText(names[model.getTurn()]+"'s turn");
    }
    
    private void initBackButtonListener() {
        view.addBackButtonListener((e) -> {
            view.dispose();
            view = null;
            model = null;
            MainMenu.getInstance().setVisible(true);
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
                view.setCircleMarkAt(pos);
                updateTurnText();
                server.sendObject(pos);
                
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
        int pos = (Integer)server.readObject();
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
