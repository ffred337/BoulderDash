package view;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LevelSelectorPanel extends JPanel {
    private Font small;
    private SoundManager sound = new SoundManager(1);

    public LevelSelectorPanel() {
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
        g.setFont(small.deriveFont(Font.ITALIC, 40f));
        g.setColor(Color.white);

        int levelSelectorX = 200, levelSelectorY = 100, x_ = 50, y_ = 50;
        String levelSelector = "LEVEL SELECTOR";
        String map_1 = "-> Press 1 for map 1";
        String map_2 = "-> Press 2 for map 2";
        String map_3 = "-> Press 3 for map 3";
        String map_4 = "-> Press 4 for map 4";
        String map_5 = "-> Press 5 for map 5";

        PanelComponent background = new PanelComponent(0, 0, "assets/logo/bg_sky.jpg");

        g.drawImage(background.getImage().getScaledInstance(1440, 768, Image.SCALE_DEFAULT), 0, 0, this);
        g.drawString(levelSelector, levelSelectorX, levelSelectorY);
        g.drawString(map_1, levelSelectorX + x_, levelSelectorY + y_);
        g.drawString(map_2, levelSelectorX + x_, levelSelectorY + y_ * 2);
        g.drawString(map_3, levelSelectorX + x_, levelSelectorY + y_ * 3);
        g.drawString(map_4, levelSelectorX + x_, levelSelectorY + y_ * 4);
        g.drawString(map_5, levelSelectorX + x_, levelSelectorY + y_ * 5);
    }
    public void playMusic(){

        sound.setFile();
        sound.play();
        sound.loop();
    }

    public void stopMusic(){

        sound.stop();
    }

}
