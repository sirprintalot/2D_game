package main;

import entity.*;
import objects.*;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

public class UI {

    GamePanel gp;
    Font MP16REG, VCR_OSD_MONO_1;
    Graphics2D g2;

    BufferedImage fullHeart, halfHeart, blankHeart, crystal_full, crystal_blank;
    public boolean messageOn = false;
//    public String message = "";
//    public int messageCounter = 0;
//    public boolean gameFinished = false;

    // item Slots
    public int slotCol = 0;
    public int slotRow = 0;

    public boolean messageDisplay = true;

    //display scrolling messages
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();


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

        // Mana display
        Entity crystal = new OBJ_manaCrystal(gp);
        crystal_full = crystal.image;
        crystal_blank = crystal.image2;


    }

    public void addMessage(String text) {

        message.add(text);
        messageCounter.add(0);
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
            drawMessage();
        }

        //PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
        }

        // CHARACTER STATE
        if (gp.gameState == gp.characterState) {
            drawCharacterScreen();
            drawInventoryScreen();
        }

        //DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            drawPlayerLife();
            drawDialogueScreen();
        }
        // Interact state
        if (gp.gameState == gp.interactState) {
            drawPlayerLife();
            drawDialogueScreen();
        }
    }

    public void drawPlayerLife() {

        //Draw MAX LIFE
        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

        while (i < gp.player.maxLife / 2) {

            g2.drawImage(blankHeart, x, y, null);
            i++;
            x += gp.tileSize;

        }

        //RESET VALUES
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;

        //DRAW CURRENT LIFE

        while (i < gp.player.life) {
            g2.drawImage(halfHeart, x, y, null);
            i++;
            if (i < gp.player.life) {
                g2.drawImage(fullHeart, x, y, null);
                i++;
                x += gp.tileSize;
            }
        }

        //Draw max mana
        x = (gp.tileSize / 2) - 5;
        y = (int) (gp.tileSize * 1.5);
        i = 0;

        while(i < gp.player.maxMana){

            g2.drawImage(crystal_blank, x, y, null);
            i++;
            x += 35;
        }

        // draw mana
        x = (gp.tileSize / 2) - 5;
        y = (int) (gp.tileSize * 1.5);
        i = 0;
        while(i < gp.player.mana){
            g2.drawImage(crystal_full, x, y, null);
            i++;
            x += 35;
        }
        

    }

    public void drawMessage() {

        int messageX = gp.tileSize;
        int messageY = gp.tileSize * 4;

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 23f));

        for (int i = 0; i < message.size(); i++) {
            if (message.get(i) != null) {

                g2.setColor(Color.BLACK);
                g2.drawString(message.get(i), messageX + 3, messageY + 3);
                g2.setColor(Color.WHITE);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1;  // messageCounter++
                messageCounter.set(i, counter); // set the counter to the array

                messageY += 25;

                //remove the message after 3 seconds
                if (messageCounter.get(i) > 125) {
                    message.remove(i);
                    messageCounter.remove(i);
                }
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

        } else if (titleScreenState == 1) {

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
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize / 2, y);
            }

            text = "Thief";
            x = getXforCenterDisplay(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize / 2, y);
            }

            text = "Sorcerer";
            x = getXforCenterDisplay(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize / 2, y);
            }

            text = "Back";
            x = getXforCenterDisplay(text);
            y += gp.tileSize * 2;
            g2.drawString(text, x, y);
            if (commandNum == 3) {
                g2.drawString(">", x - gp.tileSize / 2, y);
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
    public void drawCharacterScreen() {

        //create a frame
        final int frameX = gp.tileSize;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize * 6;
        final int frameHeight = gp.tileSize * 10;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //TEXT
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(30f));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 37;

        // NAME COLUMN
        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Life", textX, textY);
        textY += lineHeight;
        g2.drawString("Mana", textX, textY);
        textY += lineHeight;
        g2.drawString("Strength", textX, textY);
        textY += lineHeight;
        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2.drawString("Defense", textX, textY);
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
        textY = frameY + gp.tileSize;

        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforRightAlingn(value, borderX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;


        value = gp.player.life + "/" + gp.player.maxLife;
        textX = getXforRightAlingn(value, borderX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = gp.player.mana + "/" + gp.player.maxMana;
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

        value = String.valueOf(gp.player.defense);
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

        g2.drawImage(gp.player.currentWeapon.down1, borderX - (gp.tileSize - 13), textY - 38, null);
        textY += gp.tileSize;

        g2.drawImage(gp.player.currentShield.down1, borderX - (gp.tileSize - 13), textY - 45, null);

    }

    // INVENTORY SCREEN
    public void drawInventoryScreen() {

        //create a frame
        final int frameX = gp.tileSize * 9;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize * 6;
        final int frameHeight = gp.tileSize * 5;

        //Draw the sub-window
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // Slots for the items
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize + 3;

        // Cursor
        int cursorX = slotX + (slotSize * slotCol);
        int cursorY = slotY + (slotSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;

        //Draw player's items
        for (int i = 0; i < gp.player.inventory.size(); i++) {

            //Highlight the current item
            if (gp.player.inventory.get(i) == gp.player.currentWeapon || gp.player.inventory.get(i) == gp.player.currentShield) {

                g2.setColor(new Color(240, 150, 20));
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);


            }


            //draws the first item
            g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);
            //pass to the next column
            slotX += slotSize;

            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXstart;
                slotY += slotSize;

            }
        }

        // Draw cursor
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

        //Description window
        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gp.tileSize * 3;

        //Draw description text
        int descTextX = dFrameX + 20;
        int descTextY = dFrameY + gp.tileSize;

        g2.setFont(g2.getFont().deriveFont(25F));

        int itemIndex = getItemIndex();
        if (itemIndex < gp.player.inventory.size()) {

            //Only drawing the subwindow when there's an item
            drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);

            for (String line : gp.player.inventory.get(itemIndex).itemDescription.split("/n")) {
                g2.drawString(line, descTextX, descTextY);
                descTextY += 30;
            }
        }

    }

    public int getItemIndex() {

        int itemIndex = slotCol + (slotRow * 5);
        return itemIndex;
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
    public int getXforRightAlingn(String text, int borderX) {

        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = borderX - length;
        return x;
    }

    public int getXforCenterDisplay(String text) {

        int txtLen = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth / 2 - txtLen / 2;
    }


}
