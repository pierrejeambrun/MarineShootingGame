package first_package;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KeyInput extends KeyAdapter {

    private List<Integer> pressedKeys = new ArrayList<>(100);

    private List<Integer> pressedKeys2 = new ArrayList<>(100);


    private Handler handler;

    private Game game = Game.getInstance();

    public KeyInput(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //player one
        int keyCode = e.getKeyCode();
        if (keyCode != KeyEvent.VK_SPACE)
            pressedKeys.add(keyCode);
        if (pressedKeys.contains(KeyEvent.VK_Z))
            game.player.setVelY(-5);
        if (pressedKeys.contains(KeyEvent.VK_S))
            game.player.setVelY(5);
        if (pressedKeys.contains(KeyEvent.VK_Q))
            game.player.setVelX(-5);
        if (pressedKeys.contains(KeyEvent.VK_D))
            game.player.setVelX(5);
        if (keyCode == KeyEvent.VK_SPACE && !pressedKeys.contains(KeyEvent.VK_SPACE)) {
            pressedKeys.add(keyCode);
            game.player.firing = true;
        }

        //player two
//        if (keyCode != KeyEvent.VK_SHIFT)
//            pressedKeys2.add(keyCode);
//        if (pressedKeys2.contains(KeyEvent.VK_UP))
//            game.player2.setVelY(-5);
//        if (pressedKeys2.contains(KeyEvent.VK_DOWN))
//            game.player2.setVelY(5);
//        if (pressedKeys2.contains(KeyEvent.VK_LEFT))
//            game.player2.setVelX(-5);
//        if (pressedKeys2.contains(KeyEvent.VK_RIGHT))
//            game.player2.setVelX(5);
//        if (keyCode == KeyEvent.VK_SHIFT && !pressedKeys2.contains(KeyEvent.VK_SHIFT)) {
//            pressedKeys2.add(keyCode);
//            game.player2.firing = true;
//        }

        if (game.state == Game.State.Menu && keyCode == KeyEvent.VK_ENTER)
            game.state = Game.State.Game;

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_Z)
            game.player.setVelY(0);
        if (key == KeyEvent.VK_S)
            game.player.setVelY(0);
        if (key == KeyEvent.VK_Q)
            game.player.setVelX(0);
        if (key == KeyEvent.VK_D)
            game.player.setVelX(0);
        if (key == KeyEvent.VK_SPACE)
            game.player.firing = false;

//        if (key == KeyEvent.VK_UP)
//            game.player2.setVelY(0);
//        if (key == KeyEvent.VK_DOWN)
//            game.player2.setVelY(0);
//        if (key == KeyEvent.VK_LEFT)
//            game.player2.setVelX(0);
//        if (key == KeyEvent.VK_RIGHT)
//            game.player2.setVelX(0);
//        if (key == KeyEvent.VK_SHIFT && !pressedKeys2.contains(KeyEvent.VK_SHIFT))
//            game.player2.firing = false;

//        pressedKeys = pressedKeys.stream().filter(x -> x !=key).collect(Collectors.toList());
        pressedKeys.removeAll(Arrays.asList(key));
        pressedKeys2.removeAll(Arrays.asList(key));
    }
}
