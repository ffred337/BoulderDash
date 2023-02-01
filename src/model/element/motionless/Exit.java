package model.element.motionless;

import model.element.LevelController;
import model.element.Permeability;
import model.element.Sprite;

import java.io.IOException;

public class Exit extends MotionlessElement{
    private static Sprite sprite1 = new Sprite('X',"EXIT","exit.png");

    public Exit(int x, int y) throws IOException {
        super(x,y,sprite1);
    }
}
