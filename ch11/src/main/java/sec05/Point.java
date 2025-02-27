package sec05;

import com.horstmann.annotations.ToString;

@ToString(includeName = false)
public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @ToString(includeName = false)
    public int getX() {
        return x;
    }

    @ToString(includeName = false)
    public int getY() {
        return y;
    }
}