import java.awt.*;

/**
 * Created by christianbutron on 3/18/17.
 */
public class Invader extends GameObject {

    private Bullet oldBullet;
    private int counter;

    private boolean left;
    private boolean right;

    public Invader(int x, int y, int width, int height, Image img, int vel) {
        super(x, y, width, height, img, vel);
        health = 100;
        counter = 0;
    }

    @Override
    public void update() {
        if(health <= 0) {
            removeInvader();
            return;
        }

        if(counter % 1000 == 0 && !left) {
            left = true;
            right = false;
        } else if(counter % 1000 == 0 && left) {
            left = false;
            right = true;
        }

        if(counter % 20 == 0) {
            if (left) {
                x += vel;
                rect.x += vel;
            }
            if (right) {
                x -= vel;
                rect.x -= vel;
            }
        }

        if(counter % 120 == 0)
            GUI.bullets.add(new Bullet(x+width/3, y+30,15,30,Cache.bullet, 2, "Invader"));
        counter++;

        for(Bullet bullet : GUI.bullets) {
            if(rect.intersects(bullet.rect) && bullet != oldBullet && bullet.getOwner() != "Invader") {
                health -= 25;
                oldBullet = bullet;
                return;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img,x,y,width,height,null);
    }

    public void shoot() {

    }

    public void removeInvader() {
        GUI.invaders.remove(this);
    }
}
