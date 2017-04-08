import java.awt.*;

/**
 * Created by christianbutron on 3/18/17.
 */
public class Bullet extends GameObject {

    private Bullet oldBullet;
    private String owner;

    public Bullet(int x, int y, int width, int height, Image img, int vel, String owner) {
        super(x, y, width, height, img, vel);
        this.owner = owner;
        health = 1;
    }

    public String getOwner() { return owner; }

    @Override
    public void update() {
        if(health <= 0) {
            removeBullet();
            return;
        }

        if(y <= 0) {
            removeBullet();
            return;
        }

        if(y >= GUI.WIDTH) {
            removeBullet();
            return;
        }

        y += vel;
        rect.y += vel;

        for(Bullet bullet : GUI.bullets) {
            if(rect.intersects(bullet.rect) && bullet != this && bullet != oldBullet) {
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

    public void removeBullet() {
        GUI.bullets.remove(this);
    }
}
