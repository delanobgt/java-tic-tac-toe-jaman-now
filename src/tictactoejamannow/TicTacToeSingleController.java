package tictactoejamannow;

import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class TicTacToeSingleController {
    
    private TicTacToeView view = new TicTacToeView();
    private TicTacToeModel model = new TicTacToeModel();
    private TicTacToeBot bot = new TicTacToeBot();
    private volatile boolean botThinking = false;
    private String[] names;
    
    public TicTacToeSingleController() {
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
        names = new String[] {playerName, "Bot"};
        updateTurnText();
        MainMenu.getInstance().setVisible(false);
        view.setVisible(true);
    }
    
    private final void updateTurnText() {
        view.setStatText(names[model.getTurn()]+"'s turn");
    }
    
    private final void initBackButtonListener() {
        view.addBackButtonListener((e) -> {
            view.dispose();
            MainMenu.getInstance().setVisible(true);
        });
    }
    
    private final void initRestartButtonListener() {
        view.addRestartButtonListener((e) -> {
            botThinking = false;
            model = new TicTacToeModel();
            view.resetButtons();
            updateTurnText();
        });
    }
    
    private final void initButtonClickListener() {
        ActionListener[] listeners = new ActionListener[9];
        for (int i = 0; i < 9; i++) {
            final int pos = i;
            listeners[i] = (e) -> {
                if (botThinking) return;
                if (model.checkWin() != model.UNKNOWN_YET ||
                        model.checkDraw() ||
                        !model.isEmptyAt(pos)) return;
                
                model.setMarkAt(pos);
                view.setCircleMarkAt(pos);
                
                updateTurnText();
                checkEndOfTurnStatus();
                
                botPlays();
            };
        }
        view.addClickListenersToButtons(listeners);
    }
    
    private void botPlays() {
        new Thread(() -> {
            botThinking = true;
            Tool.sleep(500);
            //bot's turn
            if (model.checkWin() != model.UNKNOWN_YET ||
                    model.checkDraw()) return;

            int botPos = bot.getOptimalPosition(model.getSlots());
            model.setMarkAt(botPos);
            view.setCrossMarkAt(botPos);

            updateTurnText();
            checkEndOfTurnStatus();
            botThinking = false;
        }).start();
    }
    
    private void checkEndOfTurnStatus() {
        int status;
        if ((status = model.checkWin()) != model.UNKNOWN_YET) {
            view.setStatText(names[status]+" won!!");
            view.markWinButtons(model.getWinSlots());
        } else if (model.checkDraw()) 
            view.setStatText("It's a tie");
    }
}
