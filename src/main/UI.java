package main;

import java.awt.*;
import java.io.*;
import java.text.*;

public class UI {

    GamePanel gp;
    Font MP16REG, SHPinscher, VCR_OSD_MONO_1 ;
    Graphics2D g2;

    public boolean messageOn = false;
    public String message = "";
    public int messageCounter = 0;
    public boolean gameFinished = false;

    public String currentDialog = "";
    
    
    public UI(GamePanel gp) {

        this.gp = gp;

        try {

            //font 1
            InputStream is = getClass().getResourceAsStream("/font/VCR_OSD_MONO_1.001.ttf");
            assert is != null;
            VCR_OSD_MONO_1 = Font.createFont(Font.TRUETYPE_FONT, is);

            //font 2
            InputStream inputStream = getClass().getResourceAsStream("/font/MP16REG.ttf");
            assert inputStream != null;
            MP16REG = Font.createFont(Font.TRUETYPE_FONT, inputStream);


        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void showMessage(String text) {

        message = text;
        messageOn = true;
    }


    public void draw(Graphics2D g2) {
       
        this.g2 = g2;

        g2.setFont(VCR_OSD_MONO_1);
        g2.setColor(Color.WHITE);

        //PLAY STATE
        if (gp.gameState == gp.playState) {
            // continue with playstate
        }

        //PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }

        //DIALOGUE STATE
        if(gp.gameState == gp.dialogueState){

            drawDialogueScreen();
        }
    }

    public void drawPauseScreen() {

        String text = "Game Paused";

        int x = getXforCenterDisplay(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen(){

        //dialogue window
        int x = gp.tileSize * 2;
        int y= gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;

        drawSubWindow(x, y, width, height);

        x += (gp.tileSize/2) ;
        y += gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28f));

        // create line brakes for dialogue
        for(String line : currentDialog.split("/n")){
            g2.drawString(line, x, y);
            y += 40;
        }
        


        
    }

    public void drawSubWindow(int x, int y, int width, int height){

        //set the background rect, must be first, so the margin can be visible
        Color c = new Color(0,0,0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 40, 40);

        //set the white margin on top of the black background
        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 36, 36);



    }

    public int getXforCenterDisplay(String text) {

        int txtLen = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth / 2 - txtLen / 2;
    }


}
