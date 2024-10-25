package main;

import entity.*;
import objects.*;

import java.awt.*;
import java.awt.image.*;
import java.io.*;

public class UI {

    GamePanel gp;
    Font MP16REG, VCR_OSD_MONO_1;
    Graphics2D g2;

    BufferedImage fullHeart, halfHeart, blankHeart;
    public boolean messageOn = false;
    public String message = "";
    public int messageCounter = 0;
    public boolean gameFinished = false;

    public int commandNum = 0;

    //extra title screen substate for choosing character
    public int titleScreenState = 0; //0 = first screen(main) 1 = character selection screen


    public String currentDialogue = "";


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

        //CREATE Heart OBJECT
        Entity heart = new OBJ_Heart(gp);
        fullHeart = heart.image;
        halfHeart = heart.image2;
        blankHeart = heart.image3;

    }

    public void showMessage(String text) {

        message = text;
        messageOn = true;
    }


    public void draw(Graphics2D g2) {

        this.g2 = g2;
        g2.setFont(VCR_OSD_MONO_1);
        g2.setColor(Color.WHITE);

        //TITTLE STATE
        if (gp.gameState == gp.tittleState) {
            drawTitleScreen();
        }

        //PLAY STATE
        if (gp.gameState == gp.playState) {

            drawPlayerLife();
        }

        //PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
        }

        // CHARACTER STATE
        if(gp.gameState == gp.characterState){
              drawCharacterScreen();
        }

        //DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            drawPlayerLife();
            drawDialogueScreen();
        }
        // Interact state
        if(gp.gameState == gp.interactState){
            drawPlayerLife();
            drawDialogueScreen();
        }
    }

    public void drawPlayerLife(){

        //Draw MAX LIFE
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        while (i < gp.player.maxLife/2){

            g2.drawImage(blankHeart, x, y, null);
            i++;
            x += gp.tileSize;

        }

        //RESET VALUES
        x = gp.tileSize/2;
        y = gp.tileSize/2;
         i = 0;

         //DRAW CURRENT LIFE

        while(i < gp.player.life){
            g2.drawImage(halfHeart, x, y, null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(fullHeart, x, y, null);
                i++;
                x += gp.tileSize;
            }
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
        for (String line : currentDialogue.split("/n")) {
            g2.drawString(line, x, y);
            y += 40;
        }

    }

    //Character screen
    public void drawCharacterScreen(){
        
        //create a frame
        final int frameX = gp.tileSize ;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize * 6;
        final int frameHeight = gp.tileSize * 10;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //TEXT
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(32f));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 45;

        // NAME COLUMN
        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Life", textX, textY);
        textY += lineHeight;
        g2.drawString("Strength", textX, textY);
        textY += lineHeight;
        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2.drawString("XP", textX, textY);
        textY += lineHeight;
        g2.drawString("Next lvl", textX, textY);
        textY += lineHeight;
        g2.drawString("Coin", textX, textY);
        textY += lineHeight;
        g2.drawString("Weapon", textX, textY);
        textY += lineHeight;
        g2.drawString("Shield", textX, textY);

        //VALUES COLUMN
        int borderX = (frameX + frameWidth) - gp.tileSize;

        //Reset textY
        textY =  frameY + gp.tileSize;

        String value;
        
         value = String.valueOf(gp.player.level);
         textX = getXforRightAlingn(value, borderX);
         g2.drawString(value, textX, textY);
        textY += lineHeight;


        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXforRightAlingn(value, borderX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.strength);
        textX = getXforRightAlingn(value, borderX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.dexterity);
        textX = getXforRightAlingn(value, borderX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.attack);
        textX = getXforRightAlingn(value, borderX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.exp);
        textX = getXforRightAlingn(value, borderX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXforRightAlingn(value, borderX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.coin);
        textX = getXforRightAlingn(value, borderX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        g2.drawImage(gp.player.currentWeapon.down1, borderX - (gp.tileSize - 13), textY - 38, null );
        textY += gp.tileSize;

        g2.drawImage(gp.player.currentShield.down1, borderX - (gp.tileSize - 13), textY - 45,  null);
        

        
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

    //get the text inside player stats centered to the right
    public int getXforRightAlingn(String text, int borderX){

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = borderX - length;
        return x;
    }

    public int getXforCenterDisplay(String text) {

        int txtLen = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth / 2 - txtLen / 2;
    }


}
