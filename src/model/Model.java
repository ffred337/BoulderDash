package model;

import contract.IModel;
import entity.CollisionHandler;
import entity.DAO;
import model.element.LevelController;
import model.element.Map;

import java.io.IOException;
import java.util.Observable;
/**
 * @author Fred X2026
 *
 */

public class Model extends Observable implements IModel {
    /**
     *
     */
    private Map map;
    private boolean haveWon = false;
    private CollisionHandler collisionHandler;
    private LevelController levelController;
    private int width;
    private int height;
    final int tSize = 48;
    private DAO dao;
    Thread gameThread;

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setLevelController(LevelController levelController) {
        this.levelController = levelController;
    }

    @Override
    public CollisionHandler getCollisionHandler() {
        return this.collisionHandler;
    }

    @Override
    public void setLevelController(String levelController) {
        this.levelController = LevelController.valueOf(levelController);
    }

    //private IView view;


    public Model() throws IOException {
        this.map = new Map();
        this.dao = new DAO();
        this.levelController = LevelController.MAP5;
        this.width = this.map.getMapWidth() * this.tSize;
        this.height = this.map.getMapHeight() * this.tSize;
        this.collisionHandler = new CollisionHandler(this.getMap());
    }

    public void startGameThread(Runnable r){
        gameThread = new Thread(r);
        gameThread.start();
    }

    public Map getMap(){
        return this.map;
    }

    @Override
    public void loadMap(int code) {
        try {
            this.dao.open();
            this.setMap(new Map(code, this.dao.getLevel(code), this.levelController));
            this.dao.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    private void setMap(final Map map){
        this.map = map;
        this.collisionHandler = new CollisionHandler(this.getMap());
        this.setChanged();
        this.notifyObservers();
    }

    public Observable getObservable(){return this;}

    @Override
    public void updateGame() {
    }

    @Override
    public void viewNotify() {
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public boolean getIsAlive() {
        return this.getMap().isPlayerAlive();
    }

    @Override
    public void movePlayer(String order) throws IOException {
        if(this.map.isPlayerAlive()){
        switch (order){
            case "UP":
                this.getMap().movePlayerUp();
                break;
            case "DOWN":
                this.getMap().movePlayerDown();
                break;
            case "LEFT":
                this.getMap().movePlayerLeft();
                break;
            case "RIGHT":
                this.getMap().movePlayerRight();
                break;
            case "NOP":
                //this.model.loadMap(5);
                break;
        }
            this.viewNotify();
        }
    }

}
