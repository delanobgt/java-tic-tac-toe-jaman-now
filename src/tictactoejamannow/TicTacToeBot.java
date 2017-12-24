package tictactoejamannow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicTacToeBot {
    
    private Map<List<Integer>, Integer> memo = new HashMap<>();
    private Map<List<Integer>, Integer> optimalPosition = new HashMap<>();
    
    public Integer getOptimalMove(int[] slots) {
        return optimalPosition.get(convertToList(slots));
    }
    
    private void constructGameTree(List<Integer> slots, int turn) {
        
    }
    
    private static List<Integer> convertToList(int[] slots) {
        List<Integer> lst = new ArrayList<>();
        for (int slot : slots)
            lst.add(slot);
        return lst;
    }
 
    public static void main(String[] args) {
        
    }
    
}
