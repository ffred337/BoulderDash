package view;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LevelSelectedPanel extends JPanel {
    private Font small;

    public LevelSelectedPanel() {
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
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
    }
    private void udpate() {
        repaint();
    }

    public void setLevelSelected(Graphics g, String mapSelected) {
        g.setFont(small.deriveFont(Font.ITALIC, 40f));
        g.setColor(Color.white);

        g.drawString(mapSelected, 400, 500);
        g.drawString("PRESS ENTER...", 1000, 700);
    }
}
