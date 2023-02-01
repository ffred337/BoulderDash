package model.element.mobile;

import model.element.LevelController;
import model.element.Map;
import model.element.Permeability;
import model.element.Sprite;

import java.io.IOException;

public class Diamond extends MobileElement{

    private static Sprite sprite1 = new Sprite('D',"DIAMOND","diamond1.png");
    private static Sprite sprite2 = new Sprite('D',"DIAMOND","diamond2.png");
    private static Sprite sprite3 = new Sprite('D',"DIAMOND","diamond3.png");
    private static Sprite sprite4 = new Sprite('D',"DIAMOND","diamond4.png");
    int animation = 1;
    int animationRate = 10;
    boolean isFallen;
    int falling = 0;



    public Diamond(LevelController lc, Map map) {
        super(sprite1, Permeability.BLOCKING, lc, map);
    }

    public Diamond(int x, int y)throws IOException{
        super(x,y,sprite1);
    }

    public void update(){
        spriteCounter ++;
        if(spriteCounter > animationRate){

            switch (animation){
            case 1:
                this.setSprite(sprite1);
                sprite1.loadMlImage();
                break;
            case 2:
                this.setSprite(sprite2);
                sprite2.loadMlImage();
                break;
            case 3:
                this.setSprite(sprite3);
                sprite3.loadMlImage();
                break;
            case 4:
                this.setSprite(sprite4);
                sprite4.loadMlImage();
                break;
        }
            this.setAnimation((getAnimation()+1)%4);
            spriteCounter = 0;
        }
    }

    public int getAnimation() {
        return animation;
    }

    public void setAnimation(int animation) {
        this.animation = animation;
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
