package first_package;

import java.util.ArrayList;
import java.util.List;

public class Physics {
    public static boolean collision(GameObject gameObject, List<? extends GameObject> gameObjects) {
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject obj = gameObjects.get(i);
            if (gameObject.equals(obj))
                continue;
            if (gameObject.getHitBox().intersects(obj.getHitBox())) {
                if (obj.getId() == ID.Projectile)
                    gameObjects.remove(obj);
                if (obj.getId() == ID.Player) {
                    ((Player) obj).life--;
                    if (((Player) obj).life <= 0) {
                        gameObjects.remove(obj);
                        Game game = Game.getInstance();
                        game.state = Game.State.Menu;
                        Player e = new Player(0, Game.HEIGHT / 2, ID.Player, "/marine.png");
                        game.handler.enemies = new ArrayList<>();
                        game.player = e;
                        game.handler.objects.add(e);
                        game.level = 1;
                    }
                }
                return true;
            }
        }
        return false;
    }
}
