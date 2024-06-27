package sec04;

@ToString(includeName = false)
public class Point {
    @ToString(includeName = false)
    private final int x;
    @ToString(includeName = false)
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}