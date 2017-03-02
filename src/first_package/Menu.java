package first_package;

import java.awt.*;

public class Menu {

    public Rectangle playButton = new Rectangle(Game.WIDTH / 2 - 100, 300, 100, 50);

    public void render(Graphics graphics) {
        Font font = new Font("arial", Font.BOLD, 30);
        graphics.setFont(font);
        graphics.setColor(Color.YELLOW);
        graphics.drawString("Marine Game", Game.WIDTH / 2 - 150, 130);
        graphics.drawString("Play", Game.WIDTH / 2 - 90, 335);
        graphics.setFont(new Font("arial", Font.BOLD, 15));
        ((Graphics2D) graphics).draw(playButton);
        graphics.drawString("Press enter to play", Game.WIDTH / 2 - 120, 200);
    }
}
