package sec06;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

public class ImageDemo {
    public static BufferedImage createImage(int width, int height, PixelFunction f) {
        var image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++) {
                Color color = f.apply(x, y);
                image.setRGB(x, y, color.getRGB());
            }
        return image;
    }

    public static void main(String[] args) throws IOException {
        BufferedImage frenchFlag = ImageDemo.createImage(150, 100,
                (x, y) -> x < 50 ? Color.BLUE : x < 100 ? Color.WHITE : Color.RED);
        Path path = Path.of("flag.png");
        ImageIO.write(frenchFlag, "PNG", path.toFile());
        System.out.println("Image saved to " + path.toAbsolutePath());
    }
}
