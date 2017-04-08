/**
 * Created by christianbutron on 3/18/17.
 */
import java.awt.*;

public class Player extends GameObject {

    private Bullet oldBullet;

    public boolean left;
    public boolean right;
    public boolean up;
    public boolean down;

    Player(int x, int y, int width, int height, Image img, int vel) {
        super(x, y, width, height, img, vel);
        health = 100;
    }

    @Override
    public void update() {
        if(health <= 0) {
            //
        }

        if(left && x > 0) {
            x -= vel;
            rect.x -= vel;
        }
        if(right && x < GUI.WIDTH-width) {
            x += vel;
            rect.x += vel;
        }
        if(up && y > 0) {
            y -= vel;
            rect.y -= vel;
        }
        if(down && y < GUI.HEIGHT-height) {
            y += vel;
            rect.y += vel;
        }

        for(Bullet bullet : GUI.bullets) {
            if(rect.intersects(bullet.rect) && bullet != oldBullet) {
                health -= 10;
                oldBullet = bullet;
                return;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img,x,y,width,height,null);
    }
}
