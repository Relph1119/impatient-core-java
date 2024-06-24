package sec02;

import java.util.Arrays;

public class ArrayUtilDemo {
    public static void main(String[] args) {
        String[] friends = {"Peter", "Paul", "Mary"};
        ArrayUtil.swap(friends, 0, 1);
        System.out.println(Arrays.toString(friends));
        // Uncomment to see error message
        // Double[] result = Arrays.swap(0, 1, 1.5, 2, 3);
    }
}
