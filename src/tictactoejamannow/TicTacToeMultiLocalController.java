package tictactoejamannow;

import com.sun.org.apache.xerces.internal.util.FeatureState;
import java.awt.event.ActionListener;

public class TicTacToeMultiLocalController {
    
    private TicTacToeView view = new TicTacToeView();
    private TicTacToeModel model = new TicTacToeModel();
    private String[] names = {"FIRST", "SECOND"};
    
    public TicTacToeMultiLocalController() {
        updateTurnText();
        initButtonClickListener();
    }
    
    public final void startGame() {
        view.setVisible(true);
    }
    
    private final void updateTurnText() {
        view.setStatText(names[model.getTurn()]+"'s turn");
    }
    
    private final void initButtonClickListener() {
        ActionListener[] listeners = new ActionListener[9];
        for (int i = 0; i < 9; i++) {
            final int pos = i;
            listeners[i] = (e) -> {
                if (model.checkWin() != model.UNKNOWN_YET ||
                        model.checkDraw()) return;
                if (!model.isEmptyAt(pos)) return;
                
                model.setMarkAt(pos);
                if (model.isCrossAt(pos)) view.setCrossMarkAt(pos);
                else view.setCircleMarkAt(pos);
                updateTurnText();
                
                int status;
                if ((status = model.checkWin()) != model.UNKNOWN_YET)
                    view.setStatText(names[status]+" won!!");
                else if (model.checkDraw()) 
                    view.setStatText("It's a tie");
            };
        }
        view.addClickListenersToButtons(listeners);
    }
}
