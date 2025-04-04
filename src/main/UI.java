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

    BufferedImage fullHeart, halfHeart, blankHeart, crystal_full, crystal_blank, coin;
    public boolean messageOn = false;

    public boolean criticalMessageOn = false;

    //dialogue speed
    private int textSpeedCounter = 0;

    // player inventory
    public int playerSlotCol = 0;
    public int playerSlotRow = 0;

    // NPC inventory
    public int npcSlotCol = 0;
    public int npcSlotRow = 0;

    //Player inventory


    // options substate
    int subState = 0;

    //Transition
    int counter = 0;

    public boolean messageDisplay = true;

    //display scrolling messages
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    ArrayList<Color> messageColors = new ArrayList<>();

    // Display message letter by letter
    int charIndex = 0;
    String combinedText = "";

    public int commandNum = 0;
    //extra title screen substate for choosing character
    public int titleScreenState = 0; //0 = first screen(main) 1 = character selection screen

    public String currentDialogue = "";

    public Entity npc;


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

        //Coin
        Entity bronzeCoin = new OBJ_BronzeCoin(gp);
        coin = bronzeCoin.down1;

    }

    public void addMessage(String text, Color textColor) {

        message.add(text);
        messageCounter.add(0);
        messageColors.add(textColor);
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
            drawInventoryScreen(gp.player, true);
        }

        //DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
//            drawPlayerLife();
            drawDialogueScreen();
        }

        // Option state
        if (gp.gameState == gp.optionState) {
            drawOptionScreen();
        }

        // Game Over state
        if (gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }

        // TRansition state
        if (gp.gameState == gp.transitionState) {
            transition();
        }

        // Trade state
        if (gp.gameState == gp.tradeState) {
            drawTradeScreen();
        }
        // sleep state
        if (gp.gameState == gp.sleepState) {
            drawSleepScreen();
        }
    }

    // PLAYER'S LIFE BAR
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

        while (i < gp.player.maxMana) {

            g2.drawImage(crystal_blank, x, y, null);
            i++;
            x += 35;
        }

        // draw mana
        x = (gp.tileSize / 2) - 5;
        y = (int) (gp.tileSize * 1.5);
        i = 0;
        while (i < gp.player.mana) {
            g2.drawImage(crystal_full, x, y, null);
            i++;
            x += 35;
        }


    }

    // GAME OVER SCREEN
    public void drawGameOverScreen() {

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);


        g2.setFont(g2.getFont().deriveFont(110f));

        //SHADOW
        g2.setColor(Color.BLACK);
        String text = "GAME OVER";
        int textX = getXforCenterDisplay(text);
        int textY = gp.screenHeight / 2;
        g2.drawString(text, textX, textY);

        //MAIN
        g2.setColor(Color.RED);
        g2.drawString(text, textX - 5, textY - 5);


        // RETRY OPTION
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 35f));
        text = "RETRY";
        textX = getXforCenterDisplay(text);
        textY += gp.tileSize * 3;
        g2.drawString(text, textX, textY);
        // CUrsor
        if (commandNum == 0) {
            g2.drawString(">", textX - 20, textY);
        }


        // BACK TO MENU OPTION
        text = "Quit Game";
        textX = getXforCenterDisplay(text);
        textY += 55;
        g2.drawString(text, textX, textY);

        // CUrsor
        if (commandNum == 1) {
            g2.drawString(">", textX - 20, textY);
        }


    }


    //MESSAGE
    public void drawMessage() {

        int messageX = gp.tileSize;
        int messageY = gp.tileSize * 4;

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 23f));

        for (int i = 0; i < message.size(); i++) {
            if (message.get(i) != null) {

                //shadow
                g2.setColor(Color.BLACK);
                g2.drawString(message.get(i), messageX + 3, messageY + 3);

                // message
                g2.setColor(messageColors.get(i));
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1;  // messageCounter++
                messageCounter.set(i, counter); // set the counter to the array

                messageY += 25;

                //remove the message after 3 seconds
                if (messageCounter.get(i) > 125) {
                    message.remove(i);
                    messageCounter.remove(i);
                    messageColors.remove(i);
                    i--;
                }
            }
        }
    }

    // TITTLE SCREEN
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


//        else if (titleScreenState == 1) {
//
//            //character selection screen
//            g2.setColor(Color.white);
//            g2.setFont(g2.getFont().deriveFont(42f));
//
//            String text = "Select your Player!";
//            int x = getXforCenterDisplay(text);
//            int y = gp.tileSize * 3;
//            g2.drawString(text, x, y);
//
//            text = "Fighter";
//            x = getXforCenterDisplay(text);
//            y += gp.tileSize * 3;
//            g2.drawString(text, x, y);
//            if (commandNum == 0) {
//                g2.drawString(">", x - gp.tileSize / 2, y);
//            }
//
//            text = "Thief";
//            x = getXforCenterDisplay(text);
//            y += gp.tileSize;
//            g2.drawString(text, x, y);
//            if (commandNum == 1) {
//                g2.drawString(">", x - gp.tileSize / 2, y);
//            }
//
//            text = "Sorcerer";
//            x = getXforCenterDisplay(text);
//            y += gp.tileSize;
//            g2.drawString(text, x, y);
//            if (commandNum == 2) {
//                g2.drawString(">", x - gp.tileSize / 2, y);
//            }
//
//            text = "Back";
//            x = getXforCenterDisplay(text);
//            y += gp.tileSize * 2;
//            g2.drawString(text, x, y);
//            if (commandNum == 3) {
//                g2.drawString(">", x - gp.tileSize / 2, y);
//            }
//        }
    }

    //PAUSE SCREEN
    public void drawPauseScreen() {

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 70f));
        g2.setColor(Color.white);
        String text = "Game Paused";

        int x = getXforCenterDisplay(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    //DIALOGUE SCREEN
    public void drawDialogueScreen() {

        //dialogue window
        int x = gp.tileSize * 3;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 6);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);

        x += (gp.tileSize / 2);
        y += gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28f));

        if (npc.dialogues[npc.dialogueSet][npc.dialogueIndex] != null) {
//            currentDialogue = npc.dialogues[npc.dialogueSet][npc.dialogueIndex];
            char[] characters = npc.dialogues[npc.dialogueSet][npc.dialogueIndex].toCharArray();
            if (charIndex < characters.length) {

                double textSpeed = 2.1;
                textSpeedCounter++;
                if (textSpeedCounter >= textSpeed) {
                    String s = String.valueOf(characters[charIndex]);
                    combinedText = combinedText + s;
                    currentDialogue = combinedText;
                    gp.playSoundEffect(22);
                    charIndex++;
                    textSpeedCounter = 0;
                }
            }

            if (gp.keyH.enterPressed) {
                charIndex = 0;
                combinedText = "";

                if (gp.gameState == gp.dialogueState) {
                    npc.dialogueIndex++;
                    gp.keyH.enterPressed = false;
                }
            }
        } else {

            npc.dialogueIndex = 0;
            if (gp.gameState == gp.dialogueState) {
                gp.gameState = gp.playState;
            }

        }

        // create line brakes for dialogue
        for (String line : currentDialogue.split("#")) {
            g2.drawString(line, x, y);
            y += 40;
        }

    }

    //CHARACTER SCREEN
    public void drawCharacterScreen() {

        //create a frame
        final int frameX = gp.tileSize * 2;
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
    public void drawInventoryScreen(Entity entity, boolean cursor) {

        int frameX;
        int frameY;
        int frameWidth;
        int frameHeight;
        int slotCol;
        int slotRow;

        //player inventory
        if (entity == gp.player) {
            frameX = gp.tileSize * 12;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize * 6;
            frameHeight = gp.tileSize * 5;

            slotCol = playerSlotCol;
            slotRow = playerSlotRow;
        }
        // entity inventory
        else {
            frameX = gp.tileSize * 2;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize * 6;
            frameHeight = gp.tileSize * 5;

            slotCol = npcSlotCol;
            slotRow = npcSlotRow;
        }

        //Draw the sub-window
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // Slots for the items
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize + 3;

        // Cursor
        if (cursor) {

            int cursorX = slotX + (slotSize * slotCol);
            int cursorY = slotY + (slotSize * slotRow);
            int cursorWidth = gp.tileSize;
            int cursorHeight = gp.tileSize;
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

            int itemIndex = getItemIndex(slotCol, slotRow);
            if (itemIndex < entity.inventory.size()) {

                //Only drawing the subwindow when there's an item
                drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);

                for (String line : entity.inventory.get(itemIndex).itemDescription.split("/n")) {
                    g2.drawString(line, descTextX, descTextY);
                    descTextY += 30;
                }
            }
        }

        //Draw entity items
        for (int i = 0; i < entity.inventory.size(); i++) {

            //Highlight the current item weapon, shield and light
            if (entity.inventory.get(i) == entity.currentWeapon || entity.inventory.get(i) == entity.currentShield || entity.inventory.get(i) == entity.currentLight) {

                g2.setColor(new Color(240, 150, 20));
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);

            }

            //draws the first item
            g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);

            // display the number of stackable items
            if (entity == gp.player && entity.inventory.get(i).ammount > 1) {
                g2.setFont(g2.getFont().deriveFont(28f));
                int amountX;
                int amountY;

                String s = String.valueOf(entity.inventory.get(i).ammount);
                amountX = getXforRightAlingn(s, slotX + 41);
                amountY = slotY + gp.tileSize;

                //draw the shadow of the number
                g2.setColor(new Color(0, 0, 0));
                g2.drawString(s, amountX, amountY);

                // draw the number
                g2.setColor(Color.white);
                g2.drawString(s, amountX - 2, amountY - 2);

            }

            //pass to the next column
            slotX += slotSize;

            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXstart;
                slotY += slotSize;
            }
        }
    }

    // MAP TRANSITION
    public void transition() {
        counter++;

        g2.setColor(new Color(0, 0, 0, counter * 5));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        if (counter == 50) {
            counter = 0;
            gp.gameState = gp.playState;
            gp.currentMap = gp.eventHandler.tempMap;
            gp.player.worldX = gp.tileSize * gp.eventHandler.tempCol;
            gp.player.worldY = gp.tileSize * gp.eventHandler.tempRow;
            gp.eventHandler.previousEventX = gp.player.worldX;
            gp.eventHandler.previousEventY = gp.player.worldY;
            gp.changeArea();
        }

    }

    // TRADE SCREEN
    public void drawTradeScreen() {

        switch (subState) {
            case 0 -> trade_Select();
            case 1 -> trade_buy();
            case 2 -> trade_sell();
        }

        gp.keyH.enterPressed = false;
    }

    public void trade_Select() {

        npc.dialogueSet = 0;
        drawDialogueScreen();

        // DRAW OPTIONS WINDOW
        int x = gp.tileSize * 14;
        int y = (int) (gp.tileSize * 4.5);
        int width = gp.tileSize * 3;
        int height = (int) (gp.tileSize * 3.5);

        drawSubWindow(x, y, width, height);

        // draw text
        x += gp.tileSize - 15;
        y += gp.tileSize;
        g2.drawString("Buy", x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - 24, y);
            if (gp.keyH.enterPressed) {
                subState = 1;
            }
        }

        y += gp.tileSize;
        g2.drawString("Sell", x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - 24, y);
            if (gp.keyH.enterPressed) {
                subState = 2;
            }
        }

        y += gp.tileSize;
        g2.drawString("Leave", x, y);
        if (commandNum == 2) {
            g2.drawString(">", x - 24, y);
            if (gp.keyH.enterPressed) {
                commandNum = 0;
                npc.startDialogue(npc, 1);
//                gp.gameState = gp.dialogueState;
            }
        }


    }

    public void trade_buy() {

        //DRAW PLAYER INVENTORY
        drawInventoryScreen(gp.player, false);

        //DRAW NPC INVENTORY
//        drawInventoryScreen(gp.npc[1][1], true);
        drawInventoryScreen(npc, true);

        //DRAW HINT WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize * 9;
        int width = gp.tileSize * 7;
        int height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2.drawString("[ESC] Back", x + 24, y + 60);

        //DRAW COIN WINDOW
        x = gp.tileSize * 12;
        y = gp.tileSize * 9;
        width = gp.tileSize * 7;
        height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2.drawString("Current money: " + gp.player.coin, x + 24, y + 60);

        //DRAW PRICE WINDOW
        int itemIndex = getItemIndex(npcSlotCol, npcSlotRow);

        //if the slot is not empty, we display the price
        if (itemIndex < npc.inventory.size()) {
            x = (int) (gp.tileSize * 8);
            y = (int) (gp.tileSize);
            width = (int) (gp.tileSize * 2.5);
            height = gp.tileSize;

            //DRAW THE PRICE
            drawSubWindow(x, y, width, height);
            g2.drawImage(coin, x + 10, y + 8, 32, 32, null);

            int price = npc.inventory.get(itemIndex).price;

            String text = " " + price;
            x = getXforRightAlingn(text, gp.tileSize * 10);
            g2.drawString(text, x, y + 34);

        }
        //BUY AN ITEM
        if (gp.keyH.enterPressed) {

            int price = npc.inventory.get(itemIndex).price;
            //if a player doesn't have enough money
            if (price > gp.player.coin) {
                npc.startDialogue(npc, 2);
                subState = 0;
                // if player doesn't have enough space in inventory
            } else {
                if (gp.player.canReceiveItem(npc.inventory.get(itemIndex))) {
                    gp.player.coin -= price;
                } else {
                    subState = 0;
                    npc.startDialogue(npc, 3);
//                    drawDialogueScreen();
                }
            }

//            else if (gp.player.inventory.size() == gp.player.inventorySize) {
//                subState = 0;
//                gp.gameState = gp.dialogueState;
//                currentDialogue = "Inventory full!!";
//                drawDialogueScreen();
//            }
//            else{
//                gp.player.coin -= price;
//                gp.player.inventory.add(merchant.inventory.get(itemIndex));
//            }
        }
    }

    public void trade_sell() {
        // DRAW PLAYER INVENTORY
        drawInventoryScreen(gp.player, true);

        int x;
        int y;
        int width;
        int height;

        //DRAW HINT WINDOW
        x = gp.tileSize * 2;
        y = gp.tileSize * 9;
        width = gp.tileSize * 7;
        height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2.drawString("[ESC] Back", x + 24, y + 60);

        //DRAW COIN WINDOW
        x = gp.tileSize * 12;
        y = gp.tileSize * 9;
        width = gp.tileSize * 7;
        height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2.drawString("Current money: " + gp.player.coin, x + 24, y + 60);

        //DRAW PRICE WINDOW
        int itemIndex = getItemIndex(playerSlotCol, playerSlotRow);

        //if the slot is not empty, we display the price
        if (itemIndex < gp.player.inventory.size()) {
            x = (int) (gp.tileSize * 9.5);
            y = (gp.tileSize);
            width = (int) (gp.tileSize * 2.5);
            height = gp.tileSize;

            //DRAW THE PRICE
            drawSubWindow(x, y, width, height);
            g2.drawImage(coin, x + 10, y + 8, 32, 32, null);


            int price = (int) (gp.player.inventory.get(itemIndex).price * 0.8);

            String text = " " + price;
            x = getXforRightAlingn(text, gp.tileSize * 10);
            g2.drawString(text, x + 50, y + 34);

        }
        //SeLL AN ITEM
        if (gp.keyH.enterPressed) {

            int price = (int) (gp.player.inventory.get(itemIndex).price * 0.8);
            //PREVENT SELLING AN EQUIPPED ITEM
            if (gp.player.inventory.get(itemIndex) == gp.player.currentShield || gp.player.inventory.get(itemIndex) == gp.player.currentWeapon) {
                subState = 0;
                commandNum = 0;
                gp.gameState = gp.dialogueState;
                npc.startDialogue(npc, 4);
//                drawDialogueScreen();
            } else {
                if (gp.player.inventory.get(itemIndex).ammount > 1) {
                    gp.player.inventory.get(itemIndex).ammount--;
                    gp.player.coin += price;
                } else {
                    gp.player.inventory.remove(itemIndex);
                    gp.player.coin += price;
                }

            }
        }
    }


    // OPTIONS SCREEN
    public void drawOptionScreen() {

        // SET COLOR AND FONT SIZE
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(35F));

        // CREATE THE SUB WINDOW
        int frameX = gp.tileSize * 6;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 8;
        int frameHeight = gp.tileSize * 10;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch (subState) {

            case 0:
                options_top(frameX, frameY);
                break;

            case 1:
                options_fullScreenNotification(frameX, frameY);
                break;

            case 2:
                options_controls(frameX, frameY);
                break;

            case 3:
                options_quitGameConfirmation(frameX, frameY);
                break;

        }
        gp.keyH.enterPressed = false;

    }

    public void drawSleepScreen() {

        counter++;

        if (counter < 120) {
            gp.eManager.lighting.filterAlpha += 0.01f;
            if (gp.eManager.lighting.filterAlpha > 1f) {
                gp.eManager.lighting.filterAlpha = 1f;
            }
        }
        if (counter >= 120) {
            gp.eManager.lighting.filterAlpha -= 0.01f;
            if (gp.eManager.lighting.filterAlpha <= 0f) {
                gp.eManager.lighting.filterAlpha = 0f;
                counter = 0;
                gp.eManager.lighting.dayState = gp.eManager.lighting.day;
                gp.eManager.lighting.dayCounter = 0;
                gp.gameState = gp.playState;
                gp.player.getImage();
            }
        }
    }

    public void options_top(int frameX, int frameY) {

        int textX;
        int textY;

        // Menu title
        String text = "Options Menu";
        textX = getXforCenterDisplay(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        //SET FONT SIZE FOR MENU ITEMS
        g2.setFont(g2.getFont().deriveFont(23F));

        // FULLSCREEN ON/OFF
        textX = frameX + gp.tileSize;
        textY += gp.tileSize * 2;
        g2.drawString("Full Screen", textX, textY);
        // cursor
        if (commandNum == 0) {
            g2.drawString(">", textX - 20, textY);

            // check or uncheck the checkbox
            if (gp.keyH.enterPressed) {
                gp.fullScreenOn = !gp.fullScreenOn;
                subState = 1;
            }
        }

        // MUSIC VOLUME
        textY += gp.tileSize;
        g2.drawString("Music Volume", textX, textY);
        // cursor
        if (commandNum == 1) {
            g2.drawString(">", textX - 20, textY);
        }


        // SOUND FX VOLUME
        textY += gp.tileSize;
        g2.drawString("Sound FX Volume", textX, textY);
        // cursor
        if (commandNum == 2) {
            g2.drawString(">", textX - 20, textY);
        }

        // CONTROL
        textY += gp.tileSize;
        g2.drawString("Controls", textX, textY);
        // cursor
        if (commandNum == 3) {
            g2.drawString(">", textX - 20, textY);
            if (gp.keyH.enterPressed) {
                subState = 2;
                commandNum = 0;
            }
        }

        // QUIT GAME
        textY += gp.tileSize;
        g2.drawString("End Game", textX, textY);
        // cursor
        if (commandNum == 4) {
            g2.drawString(">", textX - 20, textY);
            if (gp.keyH.enterPressed) {
                subState = 3;
                commandNum = 0;
            }
        }

        // BACK
        String backText = "Back";
        textX = getXforCenterDisplay(backText);
        textY += gp.tileSize * 2;
        g2.drawString(backText, textX, textY);
        // cursor
        if (commandNum == 5) {
            g2.drawString(">", textX - 20, textY);
            if (gp.keyH.enterPressed) {
                gp.gameState = gp.playState;
                commandNum = 0;
            }
        }


        // FULLSCREEN CHECKBOX
        textX = frameX + gp.tileSize * 6 + 24;
        textY = frameY + gp.tileSize * 2 + 24;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 24, 24);
        if (gp.fullScreenOn) {
            g2.fillRect(textX, textY, 24, 24);
        }

        //MUSIC VOLUME
        textX -= gp.tileSize;
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 100, 24);  // 100/5 = 20px increment for display

        int volumeWidth = 20 * gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        //SOUND FX
//        textX -= gp.tileSize;
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 100, 24);
        volumeWidth = 20 * gp.soundFX.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        // save the configurations of the session
        gp.config.saveConfig();
    }

    public void options_fullScreenNotification(int frameX, int frameY) {

        int textX = frameX + gp.tileSize + 24;
        int textY = frameY + gp.tileSize * 3;

        currentDialogue = "Restart the game /nfor full screen!";
        g2.setFont(g2.getFont().deriveFont(25F));
        for (String line : currentDialogue.split("/n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        // BACK
        textY += gp.tileSize * 4;
        g2.drawString("BACK", textX, textY);
        //cursor
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed) {
                subState = 0;
            }
        }
    }

    public void options_controls(int frameX, int frameY) {

        int textX;
        int textY;

        g2.setFont(g2.getFont().deriveFont(23F));

        // TITTLE
        String text = "Controls";
        textX = getXforCenterDisplay(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + 24;
        textY += gp.tileSize;

        // DESCRIPTION
        g2.drawString("Move:", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Confirm/Attack:", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Shoot/Cast:", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Character status:", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Pause:", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Options:", textX, textY);


        // VALUES
        textX = frameX + gp.tileSize * 5 + 24;
        textY = frameY + gp.tileSize * 2;
        g2.drawString("W/S/A/D", textX, textY);
        textY += gp.tileSize;
        textX += 24;
        g2.drawString("ENTER", textX, textY);
        textY += gp.tileSize;
        textX += 24;
        g2.drawString("F", textX, textY);
        textY += gp.tileSize;
        g2.drawString("C", textX, textY);
        textY += gp.tileSize;
        g2.drawString("P", textX, textY);
        textY += gp.tileSize;
        textX -= 24;
        g2.drawString("ESC", textX, textY);

        // BACK

        String backTextOptions = "Back";

        textX = getXforCenterDisplay(backTextOptions);
        g2.drawString(backTextOptions, textX, textY + gp.tileSize + 24);

        if (commandNum == 0) {
            g2.drawString(">", textX - 20, textY + gp.tileSize + 24);
            if (gp.keyH.enterPressed) {
                subState = 0;
                commandNum = 3;
            }
        }
    }

    public void options_quitGameConfirmation(int frameX, int frameY) {

        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize * 3;

        g2.setFont(g2.getFont().deriveFont(23F));

        currentDialogue = "Quit current game and /nreturn to title screen?";

        for (String line : currentDialogue.split("/n")) {

            g2.drawString(line, textX, textY);
            textY += 40;

        }
        // YES OR NO OPTION
        String text = "YES";
        textX = getXforCenterDisplay(text);
        textY += gp.tileSize * 3;
        g2.drawString(text, textX, textY);

        if (commandNum == 0) {
            g2.drawString(">", textX - 20, textY);
            if (gp.keyH.enterPressed) {
                subState = 0;
                titleScreenState = 0;
                gp.gameState = gp.tittleState;
                gp.stopMusic();
                gp.resetGame(true);
            }
        }

        // NO
        text = "NO";
        textX = getXforCenterDisplay(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);

        if (commandNum == 1) {
            g2.drawString(">", textX - 20, textY);
            if (gp.keyH.enterPressed) {
                subState = 0;
                commandNum = 0;
            }
        }
    }


    public int getItemIndex(int slotCol, int slotRow) {

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
                                