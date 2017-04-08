/**
 * Created by christianbutron on 3/18/17.
 */
import java.awt.*;
import javax.imageio.*;
import java.io.*;

public class Cache {
    private final String IMAGE_DIR = "src/img/";
    public static Image player;
    public static Image bullet;
    public static Image invader;

    public Cache() {
        load();
    }

    public void load() {
        this.player = loadImage("ship.png");
        this.bullet = loadImage("pencil.jpg");
        this.invader = loadImage("invader.jpg");
    }

    private Image loadImage(String img) {
        try {
            return ImageIO.read(new File(IMAGE_DIR + img));
        } catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
