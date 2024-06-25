package sec05;

import java.util.Random;

public class RandomNumbers {
    private static final Random generator = new Random();

    public static int nextInt(int low, int high) {
        return low + generator.nextInt(high - low + 1);
        // Ok to access the static generator variable
    }
}