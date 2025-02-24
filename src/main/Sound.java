package main;

import javax.sound.sampled.*;
import java.net.*;

public class Sound {

    Clip clip;
    URL[] soundURL = new URL[30];
    FloatControl fc;
    int volumeScale = 3; //default volume
    float volume;

    public Sound() {

        //replace numbers by index++
        int index = 0;
        soundURL[index] = getClass().getResource("/sounds/BlueBoyAdventure.wav");
        index++;
        soundURL[index] = getClass().getResource("/sounds/coin.wav");
        index++;
        soundURL[index] = getClass().getResource("/sounds/powerup.wav");
        index++;
        soundURL[index] = getClass().getResource("/sounds/unlock.wav");
        index++;
        soundURL[index] = getClass().getResource("/sounds/fanfare.wav");
        index++;
        soundURL[index] = getClass().getResource("/sounds/blocked.wav");
        index++;
        soundURL[index] = getClass().getResource("/sounds/powerup.wav");
        index++;
        soundURL[index] = getClass().getResource("/sounds/STEP.wav");
        index++;
        soundURL[index] = getClass().getResource("/sounds/pause.wav");
        index++;
        soundURL[index] = getClass().getResource("/sounds/blade.wav");
        index++;
        soundURL[index] = getClass().getResource("/sounds/receivedamage.wav");
        index++;
        soundURL[index] = getClass().getResource("/sounds/hitmonster.wav");
        index++;
        soundURL[index] = getClass().getResource("/sounds/teleport.wav");
        index++;
        soundURL[index] = getClass().getResource("/sounds/levelup.wav");
        index++;
        soundURL[index] = getClass().getResource("/sounds/cursor.wav");
        index++;
        soundURL[index] = getClass().getResource("/sounds/burning.wav");
        index++;
        soundURL[index] = getClass().getResource("/sounds/cuttree.wav");
        index++;
        soundURL[index] = getClass().getResource("/sounds/gameover.wav");
        index++;
        soundURL[index] = getClass().getResource("/sounds/stairs.wav");
        index++;
        soundURL[index] = getClass().getResource("/sounds/Merchant.wav");

    }


    public void setFile(int i) {

        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
//            System.out.println("check volume");

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public void play() {
        clip.start();
    }

    public void checkVolume() {
        if (fc != null) {

            if (volumeScale < 0) {
                volumeScale = 0;
            }
            if (volumeScale > 5) {
                volumeScale = 5;
            }
            switch (volumeScale) {

                case 0 -> volume = -80f;
                case 1 -> volume = -20f;
                case 2 -> volume = -12f;
                case 3 -> volume = -5f;
                case 4 -> volume = 1f;
                case 5 -> volume = 6f;
            }

            fc.setValue(volume);
        }
    }

    public void loop() {
        clip.loop(clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {

        clip.stop();

    }

}
