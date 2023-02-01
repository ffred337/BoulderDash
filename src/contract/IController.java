package contract;

import controller.GameState;
import controller.LevelController;
import controller.UserOrder;

import java.io.IOException;

public interface IController {
    void control();
    void levelOrderPerform(final LevelController lc);
    void userOrderPerform(final UserOrder userOrder) throws IOException;
    GameState getGameState();
    void setGameState(GameState gs);
    void play();
}
