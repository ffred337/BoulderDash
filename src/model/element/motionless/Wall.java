package model.element.motionless;

import model.element.LevelController;
import model.element.Permeability;
import model.element.Sprite;

import java.io.IOException;

public class Wall extends MotionlessElement{
    public Wall(LevelController lc) throws IOException {
        super(new Sprite('W',lc,"WALL", "wall.png"), Permeability.BLOCKING, lc);
    }
    public Wall(int x, int y, LevelController lc) throws IOException {
        super(x, y, new Sprite('W',lc,"WALL", "wall.png"));
    }
}
