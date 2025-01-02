package main;

import java.io.*;

public class Config {

    GamePanel gp;

    public Config(GamePanel gp) {
        this.gp = gp;
    }

    public void saveConfig()  {

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("config.txt"));

            //FULL SCREEN SETTING
            if (gp.fullScreenOn) {
                bw.write("Full screen On");
            }
            if (!gp.fullScreenOn) {
                bw.write("Full screen Off");
            }

            bw.newLine();

            // Music volume
            bw.write(String.valueOf(gp.music.volumeScale));
            bw.newLine();

            //FX VOLUME
            bw.write(String.valueOf(gp.soundFX.volumeScale));
            bw.newLine();

            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void loadConfig() {

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("config.txt"));
            String s = br.readLine();

            //FULL SCREEN SETTING
            if(s.equals("Full screen On")){
                gp.fullScreenOn = true;
            }
            if(s.equals("Full screen Off")){
                gp.fullScreenOn = false;
            }

            //MUSIC VOLUME SETTING
            s = br.readLine();
            gp.music.volumeScale = Integer.parseInt(s);

            //FX VOLUME SETTING
            s = br.readLine();
            gp.soundFX.volumeScale = Integer.parseInt(s);

            br.close();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
