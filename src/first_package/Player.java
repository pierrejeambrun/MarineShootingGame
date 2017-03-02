package first_package;

import java.awt.*;

public class Player extends GameObject {

    private Weapon weapon = Weapon.Basic;

    boolean firing = false;

    public int life = 50;

    public Player(int x, int y, ID id, String modelPath) {
        super(x, y, id);
        this.modelPath = modelPath;
        loadModel();
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        forbidPlayerToGoOutOfTheScreen();
        if (firing)
            fire();
    }

    public void fire() {
        Game.getInstance().handler.addObject(new BasicProjectile(this.getX() + 60, this.getY() + 40, ID.Projectile));
        Game.getInstance().handler.addObject(new BasicGunfire(this.getX() + 60, this.getY() + 30, ID.GunFire));
        firing = false;
    }

    public void render(Graphics graphics) {
        draw(graphics);
        graphics.setColor(Color.GREEN);
        graphics.fillRect(x + 20,y+10,1*life,5);
    }

    public void forbidPlayerToGoOutOfTheScreen() {
        if (x <= -35)
            x = -35;
        if (x >= game.WIDTH - 70)
            x = game.WIDTH - 70;
        if (y <= -35)
            y = -35;
        if (y >= game.HEIGHT - 70)
            y = game.HEIGHT - 70;
    }

}
