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
        soundURL[17] = getClass().getResource("/sounds/gameover.wav");
        soundURL[18] = getClass().getResource("/sounds/stairs.wav");
        soundURL[19] = getClass().getResource("/sounds/Merchant.wav");
        soundURL[20] = getClass().getResource("/sounds/sleep.wav");
        soundURL[21] = getClass().getResource("/sounds/parry.wav");
        soundURL[22] = getClass().getResource("/sounds/speak.wav");

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
