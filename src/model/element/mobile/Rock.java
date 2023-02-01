package model.element.mobile;

import model.element.LevelController;
import model.element.Map;
import model.element.Permeability;
import model.element.Sprite;

import java.io.IOException;

public class Rock extends MobileElement{
    boolean isFallen;
    int falling = 0;

    public Rock(LevelController lc, Map map) throws IOException {
        super(new Sprite('R',lc,"ROCK", "rock.png"), Permeability.BLOCKING, lc, map);
    }
    public Rock(int x, int y,LevelController lc) throws IOException {
        super(x,y,new Sprite('R',lc,"ROCK", "rock.png"));
        this.isFallen = false;
    }


    public boolean isFallen() {
        return isFallen;
    }

    public void setFallen(boolean fallen) {
        isFallen = fallen;
    }

    public int getFalling() {
        return falling;
    }

    public void setFalling(int falling) {
        this.falling = falling;
    }
}
