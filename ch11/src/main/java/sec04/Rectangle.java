package sec04;

@ToString
public class Rectangle {
    @ToString(includeName = false)
    private final Point topLeft;
    @ToString
    private final int width;
    @ToString
    private final int height;

    public Rectangle(Point topLeft, int width, int height) {
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
    }
}