package main;

import java.awt.*;
import java.text.*;

public class UI {

    GamePanel gp;
    Font arialBlack_40, arialBlack_70;
    Graphics2D g2;

    public boolean messageOn = false;
    public String message = "";
    public int messageCounter = 0;
    public boolean gameFinished = false;

    public String currentDialog = "";
    
    
    public UI(GamePanel gp) {

        this.gp = gp;
        arialBlack_40 = new Font("Arial Black", Font.PLAIN, 40);
        arialBlack_70 = new Font("Arial Black", Font.BOLD, 70);
        
    }

    public void showMessage(String text) {

        message = text;
        messageOn = true;
    }


    public void draw(Graphics2D g2) {
       
        this.g2 = g2;

        g2.setFont(arialBlack_70);
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
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32f));

        g2.drawString(currentDialog, x, y);


        
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
