package model.element;
import java.awt.Image;
import java.awt.image.BufferedImage;

public abstract class Element implements IElement {
    private Sprite sprite;
    private Permeability permeability;
    private LevelController lc;
    protected int x;
    protected int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Element(final Sprite sprite, final Permeability permeability){
        this.setSprite(sprite);
        this.setPermeability(permeability);
    }
    public Element(final Sprite sprite, final Permeability permeability, final LevelController lc){
        this.setSprite(sprite);
        this.setPermeability(permeability);
        this.setLevelController(lc);
    }
    public Element(int x, int y, final Sprite sprite){
        this.setSprite(sprite);
        this.setX(x);
        this.setY(y);
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    public void setLevelController(LevelController lc) {
        this.lc = lc;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public Permeability getPermeability() {
        return permeability;
    }

    public void setPermeability(Permeability permeability) {
        this.permeability = permeability;
    }

    @Override
    public final BufferedImage getImage(){
        return this.getSprite().getImage();
    }
}
