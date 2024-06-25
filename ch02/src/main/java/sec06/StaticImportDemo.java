package sec06;

import static java.lang.Math.PI;
import static java.lang.Math.pow;

public class StaticImportDemo {
    public static void main(String[] args) {
        double r = 10;
        double area = 4 * PI * pow(r, 2);
        System.out.println(area);
    }
}
