package tictactoejamannow;

import java.util.Arrays;

public class Tester {
    
    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4, 5, 7, 8, 9};
        long beginTime = System.currentTimeMillis();
        int[] arr = data.clone();
        System.out.println(System.currentTimeMillis()-beginTime);
        arr[0] = 69;
        System.out.println(Arrays.toString(data));
        System.out.println(Arrays.toString(arr));
    }
    
}
