package view;

import controller.GameState;
import entity.CollisionHandler;
import model.element.IElement;
import model.element.Map;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class GamePanel extends JPanel implements Observer, Runnable{
    final int FPS = 60;
    final int tSize = 48;
    final int FPSS = 1000000000;
    private int chrono = 12000;
    Thread gameThread;
    int width;
    int height;
    Map map;
    private GameFrame gameFrame;
    IElement[][] mp;
    CollisionHandler collisionHandler;
    private Font small;
    private Image contour;
    private Image clock;
    private Image diamond;
    public GamePanel(GameFrame gf) throws IOException {
        this.gameFrame = gf;
        this.map = gf.getModel().getMap();
        this.width = gf.getModel().getWidth();
        this.height = gf.getModel().getHeight();
        this.setPreferredSize((new Dimension(width, height)));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        gf.getModel().getMap().addObserver(this);
        this.setGameFrame(gf);
        mp = gf.getModel().getMap().getMapElements();
        this.collisionHandler = gf.getModel().getCollisionHandler();
        try {
            File smal = new File("assets/fonts/Retro Gaming.ttf");
            this.small = Font.createFont(Font.TRUETYPE_FONT, smal);
            this.contour = ImageIO.read(new File("assets/logo/Contour.png"));
            this.clock = ImageIO.read(new File("assets/logo/clock-o.png"));
            this.diamond = ImageIO.read(new File("assets/logo/diamond.png"));
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
//    //public GamePanel(final GameFrame gf){
//        this.setGameFrame(gf);
//    }

    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void stopGameThread(){
        gameThread.stop();
    }


    @Override
    public void update(Observable observable, Object o) {
        this.repaint();
    }

    public void update(){
        try {
            this.collisionHandler.mechanicsManager();
            this.map.update();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        double drawInterval = FPSS/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >=1){
                //
                update();
                repaint();
                delta--;
                drawCount++;
                if(this.chrono <= 0){
                    this.chrono = 0;
                }else {
                    this.chrono -= 1;
                }
//                try {
//                    this.gameEnd();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }

            }
            if(timer >= FPSS){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        if(this.map.getPlayer() != null){
            while(col < map.getMapWidth() && row < map.getMapHeight()){
                if(mp[col][row] != null){
                    g2.drawImage(mp[col][row].getImage(), mp[col][row].getX()*tSize,mp[col][row].getY()*tSize, tSize, tSize, this);
                }
                col ++;
                x += tSize;
                if(col == map.getMapWidth()){
                    col = 0;
                    x = 0;
                    row ++;
                    y += tSize;
                }
            }
        }
        drawAllGraphics(g2);
        try {
            gameEnd();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        g2.dispose();
    }

    public void drawAllGraphics(Graphics2D g2){
        g2.drawImage(contour,500,1,500,40,this);
        g2.drawImage(clock,850,5,35,30,this);
        g2.drawImage(diamond,550,5,32,32,this);
        g2.setFont(small.deriveFont(Font.BOLD, 38f));
        g2.setColor(Color.white);
        g2.drawString("x"+String.valueOf(this.map.getDiamondCounter()),600,33);
        g2.drawString(String.valueOf(chrono/60),900,33);
    }

    public void gameEnd() throws IOException {
        try {
            if(!this.map.isPlayerAlive() || this.map.getPlayer() == null || this.chrono <= 0){
                //this.stopGameThread();
                //this.removeAll();
                this.gameFrame.revalidate();
                this.gameFrame.buildGameFrame(GameState.Loose);
            }
            else if(this.map.getHaveWon()){
                //this.stopGameThread();
                //this.removeAll();
                this.gameFrame.revalidate();
                this.gameFrame.buildGameFrame(GameState.Win);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
