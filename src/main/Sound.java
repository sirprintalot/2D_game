package main;

import javax.sound.sampled.*;
import java.net.*;

public class Sound {

    Clip clip;
    URL[] soundURL =  new URL[30];

    public Sound(){

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



    }


    public void setFile(int i){

        try{

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        }   catch (Exception e){
            
            e.printStackTrace();
        }
        
    }

    public void play(){
         clip.start();
    }

    public void loop(){
        clip.loop(clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){

        clip.stop();
        
    }
    
}
