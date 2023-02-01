package view;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundManager {
    private Clip clip;
    private String soundLocation;
    private int soundId = 0;



    public SoundManager(final int soundId){
        this.soundId = soundId;
        switch (this.soundId){
            case 0:
                this.soundLocation = "assets/sounds/Main_Theme.wav";
                break;
            case 1:
                this.soundLocation = "assets/sounds/map_music.wav";
                break;
            case 2:
                this.soundLocation = "assets/sounds/coin_sound.wav";
                break;
        }

    }

    public void setFile(){

        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(soundLocation));
            this.clip = AudioSystem.getClip();
            clip.open(ais);
        }

        catch(Exception e){

        }
    }

    public void play(){

        clip.start();
    }

    public void loop(){

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }


    public void stop(){

        clip.stop();
    }
}
