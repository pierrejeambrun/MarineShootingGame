package first_package;

import java.awt.*;

public class BasicGunfire extends GameObject{

    public BasicGunfire(int x, int y, ID id) {
        super(x, y, id);
        modelPath= "/canonfire.png";
        loadModel();
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics graphics) {
            draw(graphics);
    }
}
