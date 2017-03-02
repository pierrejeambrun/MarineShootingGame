package first_package;

import java.util.List;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Handler {

    Random r = new Random();

    List<GameObject> objects = new LinkedList<>();

    List<Enemy> enemies = new LinkedList<>();

    public Handler() {
        generateWaveOfEnemies(Spider.class, Game.getInstance().level, enemies);
    }

    public synchronized void generateWaveOfEnemies(Class<? extends GameObject> tClass, int enemiesNumber, List<Enemy> enemies) {
        if (enemies.size() == 0) {
            if (tClass == Spider.class) {
                for (int i = 0; i < enemiesNumber; i++) {
                    this.addEnemies(new Spider(Game.WIDTH, r.nextInt(Game.HEIGHT), ID.Enemy));
                }
                Game.getInstance().level++;
            }
        }
    }


    public void tick() {
        for (int i = 0; i < enemies.size(); i++)
            enemies.get(i).tick();
        for (int i = 0; i < objects.size(); i++)
            objects.get(i).tick();
        generateWaveOfEnemies(Spider.class, Game.getInstance().level, enemies);
    }

    public void render(Graphics graphics) {
        int size = objects.size();
        for (int i = 0; i < size; i++) {
            if (objects.get(i).getX() >= Game.WIDTH + 100 || objects.get(i).getX() <= -100
                    || objects.get(i).getY() >= Game.HEIGHT + 100 || objects.get(i).getY() <= -100) {
                objects.remove(i);
                i--;
                size--;
            } else
                objects.get(i).render(graphics);
        }

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).render(graphics);
        }
    }

    public void addObject(GameObject gameObject) {
        objects.add(gameObject);
    }

    public void addEnemies(Enemy enemy) {
        this.enemies.add(enemy);
    }


}
