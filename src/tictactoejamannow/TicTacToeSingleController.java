package tictactoejamannow;

import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class TicTacToeSingleController {
    
    private TicTacToeView view = new TicTacToeView();
    private TicTacToeModel model = new TicTacToeModel();
    private String[] names;
    
    public TicTacToeSingleController() {
        initBackButtonListener();
        initRestartButtonListener();
        initButtonClickListener();
    }
    
    public final void startGame() {
        String playerName = JOptionPane.showInputDialog(
                                        null, 
                                        "First Player Name:", 
                                        "Input Name", 
                                        JOptionPane.PLAIN_MESSAGE);
        names = new String[] {playerName, "Bot"};
        updateTurnText();
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
                if (model.checkWin() != model.UNKNOWN_YET ||
                        model.checkDraw() ||
                        !model.isEmptyAt(pos)) return;
                
                model.setMarkAt(pos);
                if (model.isCrossAt(pos)) view.setCrossMarkAt(pos);
                else view.setCircleMarkAt(pos);
                updateTurnText();
                
                int status;
                if ((status = model.checkWin()) != model.UNKNOWN_YET) {
                    view.setStatText(names[status]+" won!!");
                    view.markWinButtons(model.getWinSlots());
                } else if (model.checkDraw()) 
                    view.setStatText("It's a tie");
            };
        }
        view.addClickListenersToButtons(listeners);
    }
}
