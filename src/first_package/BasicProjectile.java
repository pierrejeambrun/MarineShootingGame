package first_package;

import java.awt.*;

public class BasicProjectile extends GameObject{

    public BasicProjectile(int x, int y, ID id) {
        super(x, y, id);
        modelPath = "/basicprojectile.png";
        loadModel();
    }


    public void tick(){
        x += 15;
    }

    @Override
    public void render(Graphics graphics) {
        draw(graphics);
    }

    @Override
    public Rectangle getHitBox() {
        return new Rectangle(x, y, 37, 2);
    }
}
