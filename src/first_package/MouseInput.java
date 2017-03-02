package first_package;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //public Rectangle playButton = new Rectangle(Game.WIDTH / 2 - 100, 300, 100, 50);
        int x = e.getX();
        int y = e.getY();
        if(x > Game.WIDTH / 2 - 100 && x < Game.WIDTH / 2){
            if(y< 350&& y > 300){
                Game.getInstance().state = Game.State.Game;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
