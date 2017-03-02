package first_package;

import java.awt.*;
import java.util.Random;

public class Spider extends Enemy {
    Random random = new Random();
    private int speedX = random.nextInt(4) + 1;
    private int speedY = random.nextInt(4) + 1;
    int life = 10;

    public Spider(int x, int y, ID id) {
        super(x, y, id);
        modelPath = "/spider.png";
        loadModel();
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        while (Physics.collision(this, game.handler.enemies)) {
            x += random.nextInt(10) - 5;
            y += random.nextInt(10) - 5;
        }
        if (game.player.getX() > x + 10)
            velX = speedX;
        else if (game.player.getX() < x - 10)
            velX = -speedX;
        else
            velX = 0;
        if (game.player.getY() > y + 10)
            velY = speedY;
        else if (game.player.getY() < y - 10)
            velY = -speedY;
        else
            velY = 0;
        if (Physics.collision(this, game.handler.objects))

        {
            life--;
            if (life <= 0)
                game.handler.enemies.remove(this);
        }

    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(model, x, y, 75, 75, game);
    }

    @Override
    public void render(Graphics graphics) {
        draw(graphics);
        graphics.setColor(Color.GREEN);
        graphics.fillRect(x + 20, y + 10, 3 * life, 5);
    }
}
