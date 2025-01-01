package main;

import javax.sound.sampled.*;
import java.net.*;
import java.sql.*;

public class Sound {

    Clip clip;
    URL[] soundURL = new URL[30];
    FloatControl fc;
    int volumeScale = 3; //default volume
    float volume;

    public Sound() {

        //replace numbers by index++
        soundURL[0] = getClass().getResource("/sounds/BlueBoyAdventure.wav");

        soundURL[1] = getClass().getResource("/sounds/coin.wav");

        soundURL[2] = getClass().getResource("/sounds/powerup.wav");

        soundURL[3] = getClass().getResource("/sounds/unlock.wav");

        soundURL[4] = getClass().getResource("/sounds/fanfare.wav");

        soundURL[5] = getClass().getResource("/sounds/blocked.wav");

        soundURL[6] = getClass().getResource("/sounds/powerup.wav");

        soundURL[7] = getClass().getResource("/sounds/STEP.wav");

        soundURL[8] = getClass().getResource("/sounds/pause.wav");

        soundURL[9] = getClass().getResource("/sounds/blade.wav");

        soundURL[10] = getClass().getResource("/sounds/receivedamage.wav");

        soundURL[11] = getClass().getResource("/sounds/hitmonster.wav");

        soundURL[12] = getClass().getResource("/sounds/teleport.wav");

        soundURL[13] = getClass().getResource("/sounds/levelup.wav");

        soundURL[14] = getClass().getResource("/sounds/cursor.wav");

        soundURL[15] = getClass().getResource("/sounds/burning.wav");

        soundURL[16] = getClass().getResource("/sounds/cuttree.wav");


    }


    public void setFile(int i) {

        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
            System.out.println("check volume");

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public void play() {
        clip.start();
    }

    public void checkVolume(){

        if(volumeScale < 0 ){
            volumeScale = 0;
        }
        if(volumeScale > 5){
            volumeScale = 5;
        }
        switch (volumeScale) {
            
            case 0 -> volume = -80f;
            case 1 -> volume = -20f;
            case 2 -> volume = -12f;
            case 3 -> volume = -5f;
            case 4 -> volume = 1f;
            case 5 -> volume = 5f;
        }

        fc.setValue(volume);

    }

    public void loop() {
        clip.loop(clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {

        clip.stop();

    }

}
