package sec05;

import java.awt.geom.Point2D;
import java.io.*;

public class LabeledPoint implements Serializable {
    private final String label;
    private transient Point2D.Double point;

    public LabeledPoint(String label, double x, double y) {
        this.label = label;
        this.point = new Point2D.Double(x, y);
    }

    public String toString() {
        return "%s[label=%s,point=%s]".formatted(getClass().getName(), label, point);
    }

    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeDouble(point.getX());
        out.writeDouble(point.getY());
    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        double x = in.readDouble();
        double y = in.readDouble();
        point = new Point2D.Double(x, y);
    }
}
