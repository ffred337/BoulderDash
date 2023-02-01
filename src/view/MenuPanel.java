package view;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class MenuPanel extends JPanel implements Observer {

    private boolean bool = true;
    private Font small;
    private SoundManager sound = new SoundManager(0);

    public MenuPanel() {
        super();
        try {
            File smal = new File("assets/fonts/Retro Gaming.ttf");
            this.small = Font.createFont(Font.TRUETYPE_FONT, smal);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int frameWidth = 1440, frameHeight = 768, posXB = 325, posYB = 50, posXS = 580, posYS = 550;
        PanelComponent background = new PanelComponent(0, 0, "assets/logo/bg_sky.jpg");
        PanelComponent boulderMenu = new PanelComponent(posXB, posYB, "assets/logo/bdd_logo.png");
        String Press = "PRESS ENTER";
        g.setFont(small.deriveFont(Font.BOLD, 40f));//font
        g.setColor(Color.white);
        g.drawImage(background.getImage().getScaledInstance(frameWidth, frameHeight, Image.SCALE_DEFAULT), 0, 0, this);
        g.drawImage(boulderMenu.getImage(), posXB, posYB, this);
        g.drawString(Press, posXS, posYS);

    }






    public void setDefeat(Graphics g) {

        g.setFont(small.deriveFont(Font.ITALIC, 80f));
        g.setColor(Color.white);

        g.drawString("You lost", 550, 400);
    }

    public void playMusic(){

        sound.setFile();
        sound.play();
        sound.loop();
    }

    public void stopMusic(){

        sound.stop();
    }

    @Override
    public void update(Observable observable, Object o) {
        repaint();
    }
}


