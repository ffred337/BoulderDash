package model.element.motionless;

import model.element.LevelController;
import model.element.Permeability;
import model.element.Sprite;

import java.io.IOException;

public class Dirt extends MotionlessElement{

    public Dirt(LevelController lc) throws IOException {
        super(new Sprite('T',lc,"DIRT", "dirt.png"), Permeability.PENETRABLE, lc);
    }

    public Dirt(int x, int y,LevelController lc) throws IOException {
        super(x,y,new Sprite('T',lc,"DIRT", "dirt.png"));
    }
}
