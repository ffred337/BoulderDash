package view;

import contract.IController;
import contract.IModel;
import contract.IView;
import controller.LevelController;
import controller.UserOrder;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
/**
 * @author Fred X2026
 *
 */

public class View implements IView, Runnable {
    private final GameFrame gameFrame;
    private IController controller;

    public View(final IModel model) throws IOException {
        this.gameFrame = new GameFrame(model);
        SwingUtilities.invokeLater(this);
    }
    public View(final IModel model, IController controller) throws IOException {
        this.gameFrame = new GameFrame(model, controller);
        this.controller = controller;
        this.gameFrame.setController(controller);
        SwingUtilities.invokeLater(this);
    }

    protected static LevelController keyCodeToLevelController(final int keyCode){
        switch (keyCode){
            case KeyEvent.VK_NUMPAD1 -> {
                return LevelController.MAP1;
            }
            case KeyEvent.VK_NUMPAD2 -> {
                return LevelController.MAP2;
            }
            case KeyEvent.VK_NUMPAD3 -> {
                return LevelController.MAP3;
            }
            case KeyEvent.VK_NUMPAD4 -> {
                return LevelController.MAP4;
            }
            case KeyEvent.VK_NUMPAD5 -> {
                return LevelController.MAP5;
            }
            default -> {
                return LevelController.MAP1;
            }
        }
    }

    protected static UserOrder keyCodeToUserOrder(final int keyCode){
        switch (keyCode){
            case KeyEvent.VK_Z -> {
                return UserOrder.UP;
            }
            case KeyEvent.VK_S -> {
                return UserOrder.DOWN;
            }
            case KeyEvent.VK_Q -> {
                return UserOrder.LEFT;
            }
            case KeyEvent.VK_D -> {
                return UserOrder.RIGHT;
            }
            default -> {
                return UserOrder.NOP;
            }
        }
    }

    @Override
    public void run() {
        this.gameFrame.setVisible(true);
    }

    @Override
    public void printMessage(String message) {
    }

    @Override
    public void setController(IController controller) {
        this.gameFrame.setController(controller);
    }

    @Override
    public void setController() {
        this.gameFrame.setController(this.controller);
    }

}
