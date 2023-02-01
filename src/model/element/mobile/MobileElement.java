package model.element.mobile;

import model.element.*;

import java.awt.Point;

public class MobileElement extends Element {

    private Point position;

    private Map map;
    protected int spriteCounter;

    public MobileElement(Sprite sprite, Permeability permeability, LevelController lc, Map map) {
        super(sprite, permeability, lc);
        this.setMap(map);
        this.position = new Point();
    }
    public MobileElement(int x, int y, Sprite sprite, Permeability permeability, LevelController lc, Map map){
        this(sprite, permeability, lc, map);
        this.setPosition(new Point(x,y));
    }
    MobileElement(int x, int y, Sprite sprite){
        super(x,y,sprite);
        this.position = new Point(x,y);
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Point getPosition() {
        return position;
    }
    public void setPosition(Point point) {
        this.position = point;
    }
    public int getX() {
        return this.getPosition().x;
    }
    public int getY() {
        return this.getPosition().y;
    }

    public Map getMap() {
        return map;
    }
    protected void setHasMoved(){}


}
