package main;

import java.awt.*;
import java.io.*;
import java.text.*;

public class UI {

    GamePanel gp;
    Font MP16REG, VCR_OSD_MONO_1;
    Graphics2D g2;

    public boolean messageOn = false;
    public String message = "";
    public int messageCounter = 0;
    public boolean gameFinished = false;

    public int commandNum = 0;

    //extra title screen substate for choosing character
    public int titleScreenState = 0; //0 = first screen(main) 1 = character selection screen


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

        //Tittle state
        if (gp.gameState == gp.tittleState) {
            drawTitleScreen();
        }

        //PLAY STATE
        if (gp.gameState == gp.playState) {
            // continue with playstate
        }

        //PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }

        //DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {

            drawDialogueScreen();
        }
    }

    public void drawTitleScreen() {

        // check the title screen sub-state
        if (titleScreenState == 0) {

            //Optional set the background color
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            //title name
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 63F));
            String text = "Mi vecino Tarata";
            int x = getXforCenterDisplay(text);
            int y = gp.tileSize * 3;

            // shadow

            g2.setColor(Color.GRAY);
            g2.drawString(text, x + 5, y + 5);

            //main color
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            //Character Image
            x = (gp.screenWidth / 2) - gp.tileSize;
            y += gp.tileSize * 2;
            g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

            // menu
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 35));
            text = "New Game";
            x = getXforCenterDisplay(text);
            y += gp.tileSize * 4;
            g2.drawString(text, x, y);
            //Set the cursor for the menu
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize / 2, y);
            }

            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 35));
            text = "Load Game";
            x = getXforCenterDisplay(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize / 2, y);
            }

            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 35));
            text = "Quit";
            x = getXforCenterDisplay(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize / 2, y);
            }

        }
        else if( titleScreenState == 1){

            //character selection screen
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42f));

            String text = "Select your Player!";
            int x = getXforCenterDisplay(text);
            int y = gp.tileSize * 3;
            g2.drawString(text, x, y);

            text = "Fighter";
            x = getXforCenterDisplay(text);
            y += gp.tileSize * 3;
            g2.drawString(text, x, y);
            if(commandNum == 0){
                g2.drawString(">", x - gp.tileSize/2, y);
            }

            text = "Thief";
            x = getXforCenterDisplay(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1){
                g2.drawString(">", x - gp.tileSize/2, y);
            }

            text = "Sorcerer";
            x = getXforCenterDisplay(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 2){
                g2.drawString(">", x - gp.tileSize/2, y);
            }

            text = "Back";
            x = getXforCenterDisplay(text);
            y += gp.tileSize * 2;
            g2.drawString(text, x, y);
            if(commandNum == 3){
                g2.drawString(">", x - gp.tileSize/2, y);
            }
        }


    }

    public void drawPauseScreen() {

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 70f));
        String text = "Game Paused";

        int x = getXforCenterDisplay(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen() {

        //dialogue window
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;

        drawSubWindow(x, y, width, height);

        x += (gp.tileSize / 2);
        y += gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28f));

        // create line brakes for dialogue
        for (String line : currentDialog.split("/n")) {
            g2.drawString(line, x, y);
            y += 40;
        }

    }

    public void drawSubWindow(int x, int y, int width, int height) {

        //set the background rect, must be first, so the margin can be visible
        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 40, 40);

        //set the white margin on top of the black background
        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 36, 36);


    }

    public int getXforCenterDisplay(String text) {

        int txtLen = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth / 2 - txtLen / 2;
    }


}
