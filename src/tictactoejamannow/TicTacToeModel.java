package tictactoejamannow;

import java.util.Arrays;

public class TicTacToeModel {
    
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int EMPTY = -1;
    public static final int UNKNOWN_YET = -1;
    public static final int O = 0;
    public static final int X = 1;
    
    /*
     -1 : empty 
      0 : O
      1 : X
    */
    private final int[] slot = new int[9];
    private int turn = FIRST;
    
    public TicTacToeModel() {
        Arrays.fill(slot, -1);
    }
    
    public int getMarkAt(int r, int c) {
        int pos = r*3+c;
        return getMarkAt(pos);
    }
    
    public int getMarkAt(int pos) {
        return slot[pos];
    }
    
    public void setMarkAt(int r, int c) {
        int pos = r*3+c;
        getMarkAt(pos);
    }
    
    public void setMarkAt(int pos) {
        if (turn == FIRST) {
            setCircleMarkAt(pos);
        } else if (turn == SECOND) {
            setCrossMarkAt(pos);
        }
        turn = (turn+1)%2;
    }
    
    private void setCircleMarkAt(int pos) {
        slot[pos] = O;
    }
    
    private void setCrossMarkAt(int pos) {
        slot[pos] = X;
    }
    
    public int getTurn() {
        return this.turn;
    }
    
    public boolean isEmptyAt(int pos) {
        return slot[pos] == EMPTY;
    }
    
    public boolean isCircleAt(int pos) {
        return slot[pos] == O;
    }
    
    public boolean isCrossAt(int pos) {
        return slot[pos] == X;
    }
    
    public int checkWin() {
        if (slot[0] == slot[4] && slot[4] == slot[8]) return slot[0];
        if (slot[2] == slot[4] && slot[4] == slot[6]) return slot[2];
        for (int i = 0; i < 3; i++)
            if (slot[0+i] == slot[3+i] && slot[3+i] == slot[6+i]) return slot[0+i];
        for (int i = 0; i < 3; i++)
            if (slot[0+i*3] == slot[1+i*3] && slot[1+i*3] == slot[2+i*3]) return slot[0+i*3];
        return UNKNOWN_YET;
    }
    
    public int[] getWinSlots() {
        if (slot[0] == slot[4] && slot[4] == slot[8]) 
            return new int[] {0, 4, 8};
        if (slot[2] == slot[4] && slot[4] == slot[6]) 
            return new int[] {2, 4, 6};
        for (int i = 0; i < 3; i++)
            if (slot[0+i] == slot[3+i] && slot[3+i] == slot[6+i]) 
                return new int[] {0+i, 3+i, 6+i};
        for (int i = 0; i < 3; i++)
            if (slot[0+i*3] == slot[1+i*3] && slot[1+i*3] == slot[2+i*3]) 
                return new int[] {0+i*3, 1+i*3, 2+i*3};
        return null;
    }
    
    public boolean checkDraw() {
        for (int i = 0; i < 9; i++)
            if (slot[i] == EMPTY) return false;
        return true;
    }
    
}
