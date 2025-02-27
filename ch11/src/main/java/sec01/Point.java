package sec01;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static void main(String[] args) {
        System.out.println(new Point(1, 2).equals(new Point(1, 2)));
    }

    public String toString(@ReadOnly Point this) {
        return "(" + x + "," + y + ")";
    }

    @Override
    public boolean equals(@ReadOnly Point this, @ReadOnly Object obj) {
        var other = (Point) obj;
        return x == other.x && y == other.y;
    }
}
