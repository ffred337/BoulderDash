package model.element.motionless;

import model.element.Element;
import model.element.LevelController;
import model.element.Permeability;
import model.element.Sprite;

public abstract class MotionlessElement extends Element {
    MotionlessElement(final Sprite sprite, final Permeability permeability){
       super(sprite, permeability);
   }
    MotionlessElement(final Sprite sprite, final Permeability permeability, final LevelController lc){
        super(sprite, permeability, lc);
    }

    MotionlessElement(int x, int y, Sprite sprite){
        super(x,y,sprite);
    }
}
