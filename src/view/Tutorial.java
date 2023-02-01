package view;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class Tutorial extends JPanel implements Observer {
    private Font small;

    public Tutorial() {
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
        super.paint(g);
        PanelComponent background = new PanelComponent(0, 0, "assets/logo/bg_sky.jpg");
        PanelComponent rockford = new PanelComponent(530, 153, "assets/images/PLAYER/idle1.png");
        PanelComponent diamond = new PanelComponent(630, 253, "assets/logo/diamond.png");
        g.setFont(small.deriveFont(Font.ITALIC, 35f));
        g.setColor(Color.white);
        String a = "You represent Rockford.", b = "You have to dig to collect diamonds.",
                c = "Use the keys Z, Q, S and D.", d = "Z to go UP, S to go Down, Q to go Right, D to go Left", e = "Good Luck !";

        g.drawImage(background.getImage().getScaledInstance(1440, 768, Image.SCALE_DEFAULT), 0, 0, this);
        g.drawImage(rockford.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT), 630, 150, this);
        g.drawImage(diamond.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT), 870, 200, this);
        g.drawString("Tutorial (How to play)", 200, 100);
        g.drawString("PRESS ENTER...", 1000, 700);
        g.drawString(a, 50, 200);
        g.drawString(b, 50, 250);
        g.drawString(c, 50, 300);
        g.drawString(d, 50, 350);
        g.drawString(e, 50, 550);

    }
}
