package tictactoejamannow;

public class Tool {
    
    public static void sleep(long delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException ex) {}
    }
    
}
