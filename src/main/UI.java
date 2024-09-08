package main;

import objects.*;

import java.awt.*;
import java.awt.image.*;
import java.text.*;

public class UI {

    GamePanel gp;
    Font arialBlack_40, arialBlack_80;
//    BufferedImage keyImage;
    Graphics2D g2;

    public boolean messageOn = false;
    public String message = "";
    public int messageCounter = 0;
    public boolean gameFinished = false;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");


    public UI(GamePanel gp) {

        this.gp = gp;
        arialBlack_40= new Font("Arial Black", Font.PLAIN, 40);
        arialBlack_80= new Font("Arial Black", Font.BOLD, 80);
//        OBJ_Key key = new OBJ_Key(gp);
//        keyImage = key.image;
    }

    public void showMessage(String text) {

        message = text;
        messageOn = true;
    }


    public void draw(Graphics2D g2) {
        //OG METHOD
//        if (gameFinished) {
//            g2.setFont(arialBlack_40);
//            g2.setColor(Color.red);
//            String text;
//            int textLenght;
//            int x;
//            int y;
//            text = "Congratulations game completed!";
//            textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//            x = gp.screenWidth/2 - textLenght/2;
//            y = gp.screenHeight/2 - (gp.tileSize * 3);
//            g2.drawString(text, x, y);
//            //other text
//            g2.setFont(arialBlack_80);
//            g2.setColor(Color.blue);
//            text = "Your time is: " + dFormat.format(playTime);
//            textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//            x = gp.screenWidth/2 - textLenght/2;
//            y = gp.screenHeight/2 + (gp.tileSize * 4);
//            g2.drawString(text, x, y);
//            g2.setFont(arialBlack_80);
//            g2.setColor(Color.CYAN);
//            text = "GAME FINISHED";
//            textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//            x = gp.screenWidth/2 - textLenght/2;
//            y = gp.screenHeight/2 + (gp.tileSize * 2);
//            g2.drawString(text, x, y);
//            g2.setFont(arialBlack_80);
//            g2.setColor(Color.CYAN);
//            gp.gameThread = null;
//        } else {
//            g2.setFont(arialBlack_40);
//            g2.setColor(Color.LIGHT_GRAY);
//            g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
//            g2.drawString(" x " + gp.player.hasKey, 65, 60);
//            //time
//            playTime += (double)1/60;
//            g2.drawString("time: " + dFormat.format(playTime) , gp.tileSize*10,  65);
//            //Message
//            if (messageOn) {
//                g2.setFont(g2.getFont().deriveFont(28F));
//                g2.drawString(message, gp.tileSize / 2, gp.tileSize * 6);
//                messageCounter++;
//                if (messageCounter > 120) {
//                    messageCounter = 0;
//                    messageOn = false;
//                }
//            }
//        }

        this.g2 = g2;

        g2.setFont(arialBlack_80);
        g2.setColor(Color.WHITE);

        if(gp.gameState == gp.playState){
            // continue with playstate
        }
        if(gp.gameState == gp.pauseState){
              drawPauseScreen();
        }


    }

    public void drawPauseScreen(){

        String text = "Game Paused";

        int x = getXforCenterDisplay(text);
        int y =  gp.screenHeight/2;

        g2.drawString(text, x, y);
    }

    public int getXforCenterDisplay(String text){

        int txtLen = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - txtLen/2;
         return  x;
    }


}
