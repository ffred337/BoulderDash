package view;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class Win extends JPanel implements Observer {
    private Font small;

    public Win() {
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
    public void update(Observable observable, Object o) {
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(small.deriveFont(Font.ITALIC, 80f));
        g.setColor(Color.white);
        PanelComponent background = new PanelComponent(0, 0, "assets/logo/bg_sky.jpg");
        PanelComponent trophy = new PanelComponent(0, 0, "assets/logo/trophy.jpg");
        g.drawImage(background.getImage().getScaledInstance(1440, 768, Image.SCALE_DEFAULT), 5, 0, this);
        g.drawImage(trophy.getImage().getScaledInstance(600, 600, Image.SCALE_DEFAULT), 400, 0, this);
        g.drawString("You have won", 350, 700);

    }



}
