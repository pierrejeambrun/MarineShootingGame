package first_package;

import java.awt.*;

public abstract class Enemy extends GameObject {

    public Enemy(int x, int y, ID id) {
        super(x, y, id);
    }

    public abstract void tick();


    public abstract void render(Graphics graphics);

}
