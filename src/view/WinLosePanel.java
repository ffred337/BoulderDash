package view;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class WinLosePanel extends JPanel implements Observer {
    private Font small;

    public WinLosePanel() {
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
    public void paintComponents(Graphics g) {
        super.paintComponent(g);
        g.setFont(small.deriveFont(Font.ITALIC, 80f));
        g.setColor(Color.red);
        PanelComponent background = new PanelComponent(0, 0, "assets/logo/bg_sky.jpg");
        g.drawImage(background.getImage().getScaledInstance(1440, 768, Image.SCALE_DEFAULT), 5, 0, this);
        g.drawString("You lost", 550, 400);
    }

}
