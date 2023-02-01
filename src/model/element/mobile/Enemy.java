package model.element.mobile;

import model.element.LevelController;
import model.element.Map;
import model.element.Permeability;
import model.element.Sprite;

import java.io.IOException;

public class Enemy extends MobileElement{
    private static Sprite sprite1;
    private static Sprite sprite2;
    private static Sprite sprite3;
    private static Sprite sprite4;

    int animation = 1;
    int animationRate = 7;
    boolean isFallen;
    int falling = 0;
    public Enemy(int x, int y,LevelController lc)throws IOException{
        super(x,y,new Sprite('E',lc,"ENEMY","enemy2.png"));
        loadAllImages(lc);
    }

    public void update(){
        spriteCounter ++;
        if(spriteCounter > animationRate){

            switch (animation){
                case 1:
                    this.setSprite(sprite1);
                    sprite1.loadImage();
                    break;
                case 2:
                    this.setSprite(sprite2);
                    sprite2.loadImage();
                    break;
                case 3:
                    this.setSprite(sprite3);
                    sprite3.loadImage();
                    break;
                case 4:
                    this.setSprite(sprite4);
                    sprite4.loadImage();
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
    public void loadAllImages(LevelController lc) throws IOException {
        this.sprite1 = new Sprite('E', lc,"ENEMY","enemy1.png");
        this.sprite2 = new Sprite('E', lc,"ENEMY","enemy2.png");
        this.sprite3 = new Sprite('E', lc,"ENEMY","enemy3.png");
        this.sprite4 = new Sprite('E', lc,"ENEMY","enemy4.png");
    }
}
