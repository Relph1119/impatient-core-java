package sec03;

import javax.annotation.processing.Generated;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Array;
import java.util.Arrays;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@interface Persistent {
}

@Repeatable(TestCases.class)
@interface TestCase {
    String params();

    String expected();
}

@interface TestCases {
    TestCase[] value();
}

@Persistent
class Employee {
}

class Manager extends Employee {
} // Also @Persistent

public class StandardAnnotations {
    @Generated(value = "com.horstmann.generator", date = "2015-01-04T12:08:56.235-0700")
    String message = null;

    public static <T> T[] nCopies(int n, T value) {
        Class<?> cl = value.getClass();
        @SuppressWarnings("unchecked") T[] result = (T[]) Array.newInstance(cl, n);
        for (int i = 0; i < n; i++) result[i] = value;
        return result;
    }

    @TestCase(params = "4", expected = "24")
    @TestCase(params = "0", expected = "1")
    public static long factorial(int n) {
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(nCopies(3, "Hello")));
        System.out.println(Arrays.toString(IntFunction.class.getAnnotations()));
        System.out.println(Arrays.toString(Employee.class.getAnnotations()));
        System.out.println(Arrays.toString(Manager.class.getAnnotations()));
    }

    @FunctionalInterface
    public interface IntFunction<R> {
        R apply(int value);
        // void consume(R value);
    }

    public class Point {
        // @Override public boolean equals(Point other) { return false; }
    }
}
