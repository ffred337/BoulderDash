package model.element;

import java.awt.*;

public interface IElement {

    Sprite getSprite();
    Permeability getPermeability();
    Image getImage();
    int getX();
    int getY();
    void setX(int x);
    void setY(int y);

}
