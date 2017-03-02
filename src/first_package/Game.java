package first_package;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.stream.Collectors;

public class Game extends Canvas implements Runnable {

    public static final Game INSTANCE = new Game();
    public static final int WIDTH = 1600, HEIGHT = WIDTH / 16 * 9;
    private boolean running = false;
    private Thread thread;
    public Handler handler = null;
    public Player player = null;
//    public Player player2 = null;
    private Random r;
    private BufferedImage background = null;
    public int level = 1;
    public enum State {
        Menu, Game;
    }
    public State state = State.Menu;
    private Menu menu;

    public static Game getInstance() {
        return INSTANCE;
    }

    private Game() {

        player = new Player(0, HEIGHT / 2, ID.Player, "/marine.png");
//        player2 = new Player(0, HEIGHT / 2 - 200, ID.Player, "marine_player_2.png") {
//            public void fire() {
//                handler.addObject(new BasicProjectile(this.getX() + 60, this.getY() + 40, ID.Projectile) {
//                    @Override
//                    public void tick() {
//                        x += 6;
//                    }
//                });
//                handler.addObject(new BasicProjectile(this.getX() + 60, this.getY() + 40, ID.Projectile) {
//                    @Override
//                    public void tick() {
//                        x += 10;
//                        y += 1;
//                    }
//                });
//                handler.addObject(new BasicProjectile(this.getX() + 60, this.getY() + 40, ID.Projectile) {
//                    @Override
//                    public void tick() {
//                        x += 10;
//                        y -= 1;
//                    }
//                });
//                handler.addObject(new BasicGunfire(this.getX() + 60, this.getY() + 30, ID.GunFire));
//                this.firing = false;
//            }
//        };
    }

    public void launch() {
        BufferedImageLoader bf = new BufferedImageLoader();
        try {
            background = bf.loadImage("/background.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        handler = new Handler();
        menu = new Menu();
        new Window(WIDTH, HEIGHT, "Game", Game.getInstance());
        r = new Random();
//        player2.game = this.getInstance();
//        player.game = this.getInstance();
        handler.addObject(player);
//        handler.addObject(player2);
        this.addKeyListener(new KeyInput(handler, this));
        this.addMouseListener(new MouseInput());
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
                render();
                //  waveGeneratorThread = new Thread(EnemiesUpdateRunnable.INSTANCE);
//               waveGeneratorThread.run();
            }
            if (running)
                frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics graphics = bs.getDrawGraphics();
///////////////////////////////////////////////////////
        graphics.drawImage(background, 0, 0, WIDTH, HEIGHT, getInstance());
        if (state == State.Game)
            handler.render(graphics);
        else if (state == State.Menu) {
            menu.render(graphics);
        }


///////////////////////////////////////////////////////
        graphics.dispose();
        bs.show();
        handler.objects = handler.objects.stream().filter(x -> !x.getId().equals(ID.GunFire) || Math.random() < 0.7).collect(Collectors.toList());
        Toolkit.getDefaultToolkit().sync();
    }

    public void tick() {
        if (state == State.Game)
            handler.tick();
    }

    public synchronized void stop() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException("An unexpected error has occurred during the stop", e);
        }
        running = false;
    }

    public static void main(String... args) {
//        Game mytest = new Game();
        Game.getInstance().launch();
    }
}
