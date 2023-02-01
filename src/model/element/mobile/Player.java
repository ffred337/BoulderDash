package model.element.mobile;

import controller.UserOrder;
import model.element.LevelController;
import model.element.Map;
import model.element.Permeability;
import model.element.Sprite;

import java.io.IOException;

public class Player extends MobileElement{
    private static Sprite    idle1 = new Sprite('P',"PLAYER","idle1.png");
    private static Sprite    idle2 = new Sprite('P',"PLAYER","idle2.png");
    private static Sprite    down1 = new Sprite('P',"PLAYER","down1.png");
    private static Sprite    down2 = new Sprite('P',"PLAYER","down2.png");
    private static Sprite    up1 = new Sprite('P',"PLAYER","up1.png");
    private static Sprite    up2 = new Sprite('P',"PLAYER","up2.png");
    private static Sprite    left1 = new Sprite('P',"PLAYER","left1.png");
    private static Sprite    left2 = new Sprite('P',"PLAYER","left2.png");
    private static Sprite    right1 = new Sprite('P',"PLAYER","right1.png");
    private static Sprite    right2 = new Sprite('P',"PLAYER","right2.png");
    private static Sprite    dead = new Sprite('P',"PLAYER","dead.png");
    private int spriteNum = 1;



    int animation = 1;
    int animationRate = 3;
    private boolean alive;

    public Player(int x, int y)throws IOException{
        super(x,y,right1);
        this.alive = true;
    }

    public void moveOnUpdate(String order){
        switch (order){
            case "UP":
                updatedSprite(up1);
                break;
            case "DOWN":
                updatedSprite(down2);
                break;
            case "LEFT":
                updatedSprite(left1);
                break;
            case "RIGHT":
                updatedSprite(right1);
                break;
            case "NOP":
                updatedSprite(idle2);
                break;
        }
    }
    public void moveUp(){
        updatedSprite(up1);
    }

    public void moveDown(){
        updatedSprite(down1);

    }
    public void moveLeft(){
        updatedSprite(left1);

    }
    public void moveRight(){
        updatedSprite(right1);

    }

    public void update(String order){
        if(this.getAlive()) {
            spriteCounter++;
            if (spriteCounter > 2) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }


                switch (order) {
                    case "UP":
                        if (spriteNum == 1) {
                            updatedSprite(up1);
                        } else if (spriteNum == 2) {
                            updatedSprite(up2);
                        }
                        break;
                    case "DOWN":
                        if (spriteNum == 1) {
                            updatedSprite(down1);
                        } else if (spriteNum == 2) {
                            updatedSprite(down2);
                        }
                        break;
                    case "LEFT":
                        if (spriteNum == 1) {
                            updatedSprite(left1);
                        } else if (spriteNum == 2) {
                            updatedSprite(left2);
                        }
                        break;
                    case "RIGHT":
                        if (spriteNum == 1) {
                            updatedSprite(right1);
                        } else if (spriteNum == 2) {
                            updatedSprite(right2);
                        }
                        break;
                    case "NOP":
                        if (spriteNum == 1) {
                            updatedSprite(idle1);
                        } else if (spriteNum == 2) {
                            updatedSprite(idle2);
                        }
                        break;
                }
                spriteCounter = 0;
            }
        }
    }

    public Boolean getAlive() {
        return alive;
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
    }

    public void die(){
        updatedSprite(dead);
        setAlive(false);
    }

    public void updatedSprite(Sprite sprite){
        this.setSprite(sprite);
            sprite.loadMlImage();

    }
}
