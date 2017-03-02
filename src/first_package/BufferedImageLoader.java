package first_package;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BufferedImageLoader {
    private BufferedImage image;

    public BufferedImage loadImage(String path) throws IOException {
        image = ImageIO.read(BufferedImageLoader.class.getClassLoader().getResource("models/" + path));
        return image;
    }
}
