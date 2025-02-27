package sec06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.IntFunction;

public class ArrayUtil {
    public static <T> T[] repeat(int n, T obj, IntFunction<T[]> constr) {
        T[] result = constr.apply(n);
        for (int i = 0; i < n; i++) result[i] = obj;
        return result;
    }

    public static int[] repeat(int n, int obj, IntFunction<int[]> constr) {
        int[] result = constr.apply(n);
        for (int i = 0; i < n; i++) result[i] = obj;
        return result;
    }

    public static <T> T[] repeat(int n, T obj, T[] array) {
        T[] result;
        if (array.length >= n)
            result = array;
        else {
            @SuppressWarnings("unchecked") var newArray
                    = (T[]) java.lang.reflect.Array.newInstance(
                    array.getClass().getComponentType(), n);
            result = newArray;
        }
        for (int i = 0; i < n; i++) result[i] = obj;
        return result;
    }

    @SafeVarargs
    public static <T> ArrayList<T> asList(T... elements) {
        var result = new ArrayList<T>();
        for (T e : elements) result.add(e);
        return result;
    }

    private static <T> T[] repeat(int n, T obj, Class<T> cl) {
        @SuppressWarnings("uncheck") T[] result = (T[]) java.lang.reflect.Array.newInstance(cl, n);
        for (int i = 0; i < n; i++) {
            result[i] = obj;
        }
        return result;
    }

    public static void main(String[] args) {
        String[] greetings = ArrayUtil.repeat(10, "Hi", String.class);
        System.out.println(Arrays.toString(greetings));
    }


}
