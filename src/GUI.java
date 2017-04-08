import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GUI extends Canvas implements Runnable, KeyListener {

    private Thread thread;
    private JFrame frame;
    private BufferStrategy bs;
    private Graphics graphics;

    public static final int WIDTH = 960;
    public static final int HEIGHT = 540;

    private boolean running = false;
    private Cache cache;

    public static Player player;
    public static List<Invader> invaders = new CopyOnWriteArrayList<>();
    public static List<Bullet> bullets = new CopyOnWriteArrayList<>();

    public GUI() {
        frame = new JFrame("Game");
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        cache = new Cache();

        player = new Player(WIDTH/2-20,HEIGHT-120,45,45,cache.player, 2);

        for(int i = 0; i < 10; i++) {
            invaders.add(new Invader(45+i*90, 25, 45, 25,cache.invader, 1));
        }

        for(int i = 0; i < 10; i++) {
            invaders.add(new Invader(45+i*90, 75, 45, 25,cache.invader, -1));
        }

        frame.add(this);

        thread = new Thread(this);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.addKeyListener(this);
    }

    public void paint(Graphics g) {
        if(bs == null) {
            createBufferStrategy(2);
            bs = getBufferStrategy();
            graphics = bs.getDrawGraphics();

            thread.start();
            running = true;
        }
    }

    public void update() {
        player.update();

        for(Invader invader : invaders) {
            invader.update();
        }

        for(Bullet bullet : bullets) {
            bullet.update();
        }
    }

    public void render() {
        graphics.clearRect(0, 0, WIDTH, HEIGHT);

        graphics.setColor(Color.black);
        graphics.fillRect(0,0,WIDTH,HEIGHT);

        graphics.setColor(Color.green);

        player.draw(graphics);

        for(Invader invader : invaders) {
            invader.draw(graphics);
        }

        for(Bullet bullet : bullets) {
            bullet.draw(graphics);
        }
    }

    @Override
    public void run() {
        while(running) {
            update();
            render();
            bs.show();

            Thread.currentThread();
            try {
                thread.sleep(10);
            } catch(InterruptedException e) { }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.left = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.right = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_UP) {
            player.up = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.left = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.right = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_UP) {
            player.up = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.down = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            bullets.add(new Bullet(player.x+player.width/3, player.y-30,15,30,cache.bullet, -4, "Player"));
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}