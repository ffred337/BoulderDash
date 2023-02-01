package contract;


import controller.UserOrder;
import entity.CollisionHandler;
import model.element.Map;

import java.io.IOException;
import java.util.Observable;

public interface IModel {
    Map getMap();
    void loadMap(int code);
    Observable getObservable();
    void updateGame();

    void viewNotify();
    boolean getIsAlive();
    void movePlayer(String order) throws IOException;
    void startGameThread(Runnable r);

    int getWidth();
    int getHeight();
    CollisionHandler getCollisionHandler();

    void setLevelController(String levelController);
}
