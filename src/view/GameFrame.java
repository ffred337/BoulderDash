package view;

import contract.IController;
import contract.IModel;
import controller.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

/**
 * @author Fred X2026
 *
 */

public class GameFrame extends JFrame implements KeyListener{
    private Image icon;
    private static String iconLocation = "assets/logo/bdd_logo.png";
    private IModel model;

    public IModel getModel() {
        return model;
    }

    public void setModel(IModel model) {
        this.model = model;
    }

    public IController getController() {
        return this.controller;
    }

    public void setController(IController controller) {
        this.controller = controller;
    }
    private GamePanel gamePanel;
    private MenuPanel menuPanel;
    private LevelSelectorPanel levelSelectorPanel;
    private LevelSelectedPanel levelSelectedPanel;
    private Tutorial tutorialPanel;
    private WinLosePanel wlPanel;
    private Win winPanel;

    private IController controller;

    public GameFrame(final IModel model) throws IOException {
        this.menuPanel = new MenuPanel();
        this.menuPanel.addKeyListener(this);
        this.addKeyListener(this);
        this.setModel(model);
        this.buildGameFrame(GameState.MainMenu);
        this.icon = Toolkit.getDefaultToolkit().getImage(iconLocation);
        this.setIconImage(icon);
        this.setTitle("Boulder Dash");
        this.menuPanel.addKeyListener(this);

    }
    public GameFrame(final IModel model, IController controller) throws IOException {
        this.menuPanel = new MenuPanel();
        this.levelSelectorPanel = new LevelSelectorPanel();
        this.levelSelectedPanel = new LevelSelectedPanel();
        this.tutorialPanel = new Tutorial();
        this.wlPanel = new WinLosePanel();
        this.winPanel = new Win();


        this.setController(controller);
        this.addKeyListener(this);
        this.setModel(model);
        this.buildGameFrame(GameState.MainMenu);
        this.icon = Toolkit.getDefaultToolkit().getImage(iconLocation);
        this.setIconImage(icon);
        this.setTitle("Boulder Dash");
        this.menuPanel.addKeyListener(this);

    }
    public void setStartMenu(){
        this.menuPanel.playMusic();
        //this.menuPanel.setStartMenu(getGraphics());
        this.setContentPane(this.menuPanel);
    }

    public void setLevelSelection(){
        this.levelSelectorPanel.paintComponent(getGraphics());
        this.setContentPane(this.levelSelectorPanel);
    }

    public void setLevelSelected(String mapSelected){
        this.levelSelectedPanel.setLevelSelected(getGraphics(), mapSelected);
        this.setContentPane(this.levelSelectedPanel);
        this.getController().setGameState(GameState.LevelSelected);
    }

    public void setVictory(){
        this.winPanel.paintComponent(getGraphics());
        this.setContentPane(winPanel);
        //this.getController().setGameState(GameState.Win);
    }

    public void setDefeat(){
        this.wlPanel.paintComponents(getGraphics());
        this.setContentPane(wlPanel);
        //this.getController().setGameState(GameState.Loose);
    }

    public void setTutorial(){
        this.tutorialPanel.paintComponent(getGraphics());
        this.setContentPane(this.tutorialPanel);
    }

    public MenuPanel getPanel(){

        return menuPanel;
    }

    public void buildGameFrame(GameState gs) throws IOException {
        if(gs == GameState.MainMenu){
            this.setModel(model);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLayout(null);
            this.setSize(1440, 768);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setVisible(true);
            this.setStartMenu();
            this.setContentPane(this.menuPanel);
        }
        else if(gs == GameState.LevelSelector){
            this.menuPanel.stopMusic();
            //Level selector screen
            this.levelSelectorPanel.playMusic();
            this.setModel(model);
            this.setTitle("BOULDER DASH");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setLayout(null);
            this.setSize(1440, 768);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setVisible(true);
            this.setLevelSelection();
        }
        else if(gs == GameState.Tutorial){
            //Tutorial screen
            this.setTutorial();
        }
        else if(gs == GameState.Game){
            this.setModel(model);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(1440, 768);
            this.gamePanel = new GamePanel(this);
            this.gamePanel.startGameThread();
            this.setContentPane(gamePanel);
            gamePanel.addKeyListener(this);//for the other cases add keylistenner like that
            this.pack();
            this.setLocationRelativeTo(null);
            this.setResizable(false);
        }
        else if(gs == GameState.Win){
            //Win screen
            //When you'll create winOrLose Panel, you will use keylistenner like " winPanel.addKeyListener(this)
            this.setVictory();
            this.levelSelectorPanel.stopMusic();
            this.setTitle("YOU HAVE WON");
            this.gamePanel.stopGameThread();
        }
        else if(gs == GameState.Loose){
            //Loose screen
            this.setDefeat();
            this.levelSelectorPanel.stopMusic();
            this.setTitle("YOU LOST");
            this.gamePanel.stopGameThread();
        }

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            //MainMenu -> LevelSelector
            if(this.getController().getGameState() == GameState.MainMenu){
                this.getController().setGameState(GameState.LevelSelector);
                try {
                    this.buildGameFrame(this.getController().getGameState());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            //LevelSelector -> LevelSelected
            else if(this.getController().getGameState() == GameState.LevelSelector){
                this.getController().setGameState(GameState.LevelSelected);
                //Level selector screen
                try {
                    this.buildGameFrame(this.getController().getGameState());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            //LevelSelected -> Tutorial
            else if(this.getController().getGameState() == GameState.LevelSelected){
                this.getController().setGameState(GameState.Tutorial);
                //Level selector screen
                try {
                    this.buildGameFrame(this.getController().getGameState());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            //Tutorial -> Game
            else if(this.getController().getGameState() == GameState.Tutorial){
                this.getController().setGameState(GameState.Game);
                try {
                    this.buildGameFrame(this.getController().getGameState());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else if(this.getController().getGameState() == GameState.Game){
                this.getController().setGameState(GameState.Loose);
                try {
                    this.buildGameFrame(this.getController().getGameState());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else if(this.getController().getGameState() == GameState.Win || this.getController().getGameState() == GameState.Loose){
                this.getController().setGameState(GameState.MainMenu);
                try {
                    this.buildGameFrame(this.getController().getGameState());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        if(this.getController().getGameState() == GameState.LevelSelector){
            this.getController().levelOrderPerform(View.keyCodeToLevelController(e.getKeyCode()));
            int keyCode = e.getKeyCode();

            if(keyCode == KeyEvent.VK_NUMPAD1){

                this.setLevelSelected("-> map 1 selected");
            }

            if(keyCode == KeyEvent.VK_NUMPAD2){

                this.setLevelSelected("-> map 2 selected");
            }

            if(keyCode == KeyEvent.VK_NUMPAD3){

                this.setLevelSelected("-> map 3 selected");
            }

            if(keyCode == KeyEvent.VK_NUMPAD4){

                this.setLevelSelected("-> map 4 selected");
            }

            if(keyCode == KeyEvent.VK_NUMPAD5){

                this.setLevelSelected("-> map 5 selected");
            }

        }
        else if(this.getController().getGameState() == GameState.Game){
            try{
            this.getController().userOrderPerform(View.keyCodeToUserOrder(e.getKeyCode()));
        } catch (IOException er) {
            er.printStackTrace();
        }
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
