package controller;

import contract.IController;
import contract.IModel;
import contract.IView;

import java.io.IOException;

/**
 * @author Fred X2026
 *
 */
public class Controller implements IController {
    private IView view;
    private IModel model;
    private GameState gameState;

    public void setView(IView view) {
        this.view = view;
    }

    public void setModel(IModel model) {
        this.model = model;
    }

    public Controller(final IView view, final IModel model){
        this.setView(view);
        this.setModel(model);
        this.gameState = GameState.MainMenu;
    }
    public Controller(){
        this.gameState = GameState.MainMenu;
    }
    public void play(){
        this.model.updateGame();
    }
    @Override
    public void control() {

    }

    public GameState getGameState() {
        return gameState;
    }

    @Override
    public void setGameState(GameState gs) {
        this.gameState = gs;
    }

    @Override
    public void levelOrderPerform(final LevelController lc) {
        this.model.setLevelController(String.valueOf(lc));
        //To do ->
        //this.model.set
        switch (lc){
            case MAP1:
                this.model.loadMap(1);
                break;
            case MAP2:
                this.model.loadMap(2);
                break;
            case MAP3:
                this.model.loadMap(3);
                break;
            case MAP4:
                this.model.loadMap(4);
                break;
            case MAP5:
                this.model.loadMap(5);
                break;
        }
    }

    @Override
    public void userOrderPerform(final UserOrder userOrder) {
        try {
            this.model.movePlayer(String.valueOf(userOrder));
            this.model.getMap().getPlayer().update(String.valueOf(userOrder));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
