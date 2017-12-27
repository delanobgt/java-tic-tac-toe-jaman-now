package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class TicTacToeBot {
    
    private Map<String, Integer> memo = new HashMap<>();
    private Map<String, Integer> optimalPositions = new HashMap<>();
    
    public TicTacToeBot() {
        int[] slots = new int[9];
        Arrays.fill(slots, -1);
        constructGameTree(convertToList(slots), 0);
    }
    
    public Integer getOptimalPosition(int[] slots) {
        String key = convertToString(convertToList(slots));
        return optimalPositions.get(key);
    }
    
    /*
       1 : X wins
       0 : draw
      -1 : O wins
        // 0 is O's turn, 1 is X's turn
        // O will minimize, X will maximize
    */
    private Integer constructGameTree(List<Integer> slots, int turn) {
        
//        System.out.println(slots.toString());
        String key = convertToString(slots);
        if (memo.containsKey(key)) return memo.get(key);
        if (checkDraw(slots)) return 0;
        int win;
        if ((win = checkWin(slots)) != -1) return win == 0 ? -1 : 1;
        
        int optimalPosition = -1;
        int bestResult = (turn == 0) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        BiFunction<Integer, Integer, Integer> getBest = (turn == 0) ? Math::min : Math::max;
        for (int i = 0; i < slots.size(); i++) {
            if (slots.get(i) == -1) {
                slots.set(i, turn);
                Integer newResult = getBest.apply(bestResult, constructGameTree(slots, (turn+1)%2));
                if (bestResult != newResult) {
                    bestResult = newResult;
                    optimalPosition = i;
                }
                slots.set(i, -1); //reset pos-i slot to empty state
            }
        }
        
        optimalPositions.put(key, optimalPosition);
        memo.put(key, bestResult);
        return bestResult;
    }
    
    private static int checkWin(List<Integer> lst) {
        if ( lst.get(0).equals(lst.get(4)) && lst.get(4).equals(lst.get(8)) ) return lst.get(0);
        if ( lst.get(2).equals(lst.get(4)) && lst.get(4).equals(lst.get(6)) ) return lst.get(2);
        for (int i = 0; i < 3; i++)
            if ( lst.get(0+i).equals(lst.get(3+i)) && lst.get(3+i).equals(lst.get(6+i)) ) return lst.get(0+i);
        for (int i = 0; i < 3; i++)
            if ( lst.get(0+i*3).equals(lst.get(1+i*3)) && lst.get(1+i*3).equals(lst.get(2+i*3)) ) return lst.get(0+i*3);
        return -1;
    }
    
    public boolean checkDraw(List<Integer> lst) {
        for (int i = 0; i < 9; i++)
            if (lst.get(i).equals(-1)) return false;
        return true;
    }
    
    private static String convertToString(List<Integer> lst) {
        StringBuilder sb = new StringBuilder();
        for (Integer i : lst) 
            sb.append(i);
        return sb.toString();
    }
    
    private static List<Integer> convertToList(int[] slots) {
        List<Integer> lst = new ArrayList<>();
        for (int slot : slots)
            lst.add(slot);
        return lst;
    }
    
}
