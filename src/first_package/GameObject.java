package first_package;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class GameObject {
    protected int x, y;
    protected ID id;
    protected int velX, velY;
    protected BufferedImage model = null;
    protected Game game = Game.getInstance();
    protected String modelPath = null;

    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public void loadModel() {
        BufferedImageLoader bufferedImageLoader = new BufferedImageLoader();

        try {
            model = bufferedImageLoader.loadImage(modelPath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void tick();

    public abstract void render(Graphics graphics);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ID getId() {
        return id;
    }

    public GameObject setX(int x) {
        this.x = x;
        return this;
    }

    public GameObject setY(int y) {
        this.y = y;
        return this;
    }

    public GameObject setId(ID id) {
        this.id = id;
        return this;
    }

    public int getVelX() {
        return velX;
    }

    public int getVelY() {
        return velY;
    }

    public GameObject setVelX(int velx) {
        this.velX = velx;
        return this;
    }

    public GameObject setVelY(int vely) {
        this.velY = vely;
        return this;
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(model, this.getX(), this.getY(), game);
    }

    public Rectangle getHitBox() {
        return new Rectangle(x, y, 50, 50);
    }
}
