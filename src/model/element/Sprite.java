package model.element;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprite {
    private BufferedImage image;
    private String imageName;
    private String imageLocation;
    private String imageFolder;
    private char consoleImage;
    private boolean imageLoaded;

    public Sprite(final char character, final String imageName){
        this.setConsoleImage(character);
        this.setImageName(imageName);
    }
    public Sprite(final char character) {
        this(character, "noimage.jpg");
    }

    public Sprite(final char character, final LevelController lc, final String imageFolder, final String imageName) throws IOException {
        this.setConsoleImage(character);
        this.setImageFolder(imageFolder);
        this.setImageName(imageName);
        this.assignImageLocation(lc);
        this.loadImage();
    }
    public Sprite(final char character, final String imageFolder, final String imageName){
        this.setConsoleImage(character);
        this.setImageFolder(imageFolder);
        this.setImageName(imageName);
        this.loadMlImage();
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
    public void setImageFolder(String imageFolder) {
        this.imageFolder = imageFolder;
    }
    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public void assignImageLocation(LevelController lc){
        switch (lc){
            case MAP1 -> setImageLocation("MAP1");
            case MAP2 -> setImageLocation("MAP2");
            case MAP3 -> setImageLocation("MAP3");
            case MAP4 -> setImageLocation("MAP4");
            case MAP5 -> setImageLocation("MAP5");
        }
    }

    public final void loadImage(){
        try{
            this.setImage(ImageIO.read(new File("assets/images/" + this.imageLocation + "/" + this.imageFolder + "/" + this.imageName)));
        } catch (IOException e) {
        e.printStackTrace();
        }
    }

    public void loadMlImage(){
        try{
            this.setImage(ImageIO.read(new File("assets/images/" + this.imageFolder + "/" + this.imageName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public char getConsoleImage() {
        return consoleImage;
    }

    public void setConsoleImage(char consoleImage) {
        this.consoleImage = consoleImage;
    }

    public boolean isImageLoaded() {
        return imageLoaded;
    }

    public void setImageLoaded(boolean imageLoaded) {
        this.imageLoaded = imageLoaded;
    }
}
