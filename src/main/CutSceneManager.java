package main;

import entity.*;
import monster.*;
import objects.*;

import java.awt.*;
import java.util.*;

public class CutSceneManager {

    GamePanel gp;
    Graphics2D g2;
    public int sceneNum;
    public int scenePhase;
    public int counter = 0;
    float alpha = 0f;
    int y;
    String endCredit;

    //Scene number
    public final int NA = 0;
    public final int skeletonLord = 1;

    public final int ending = 2;

    public CutSceneManager(GamePanel gp) {
        this.gp = gp;

        endCredit = "Created By: mca\n" +
                "music by: mca\n" +
                "\n\n\n\n\n\n\n\n\n\n\n\n\n\n"+
                "Story by: mca\n"+
                "Thanks to: \n"+
                "Thanks to: \n"+
                "Thanks to: \n"+
                "Thanks to: \n";

    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        // for multiple cutScenes
        switch (sceneNum) {
            case skeletonLord:
                scene_skeletonLord();
                break;
            case ending:
                scene_ending();
                break;
        }

    }

    public void scene_skeletonLord() {

        // freeze the boss and the player and close the doors
        if (scenePhase == 0) {
            gp.bossBattleOn = true;

            // close the iron gate
            for (int i = 0; i < gp.obj[1].length; i++) {
                if (gp.obj[gp.currentMap][i] == null) {
                    gp.obj[gp.currentMap][i] = new OBJ_IronDoor(gp);
                    gp.obj[gp.currentMap][i].worldX = gp.tileSize * 25;
                    gp.obj[gp.currentMap][i].worldY = gp.tileSize * 28;
                    gp.obj[gp.currentMap][i].temp = true;
                    gp.playSoundEffect(26);
                    break;
                }
            }

            // search an empty slot on the npc array to put the dummy
            for (int i = 0; i < gp.npc[1].length; i++) {
                if (gp.npc[gp.currentMap][i] == null) {
                    //create the dummy
                    gp.npc[gp.currentMap][i] = new PlayerDummy(gp);

                    //position the dummy on the players last position
                    gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
                    gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
                    gp.npc[gp.currentMap][i].direction = gp.player.direction;
                    break;
                }
            }

            gp.player.drawing = false;
            scenePhase++;
        }

        // Move the camera and stop it 
        if (scenePhase == 1) {
            gp.player.worldY -= 2;

            if (gp.player.worldY < gp.tileSize * 19) {
                scenePhase++;
            }
        }

        // Unfreeze the boss
        if (scenePhase == 2) {
            //search the boss on the monster array
            for (int i = 0; i < gp.monster[1].length; i++) {
                if (gp.monster[gp.currentMap][i] != null && Objects.equals(gp.monster[gp.currentMap][i].name, MON_SkeletonLord.monName)) {
                    gp.monster[gp.currentMap][i].sleep = false;

                    // make the boss talk
                    gp.ui.npc = gp.monster[gp.currentMap][i];
                    scenePhase++;
                    break;
                }
            }
        }

        //Boss speak
        if (scenePhase == 3) {
            gp.stopMusic();
            gp.ui.drawDialogueScreen();
        }

        // Return the camera to the player
        if (scenePhase == 4) {
            //search and eliminate the dummy
            for (int i = 0; i < gp.npc[1].length; i++) {
                if (gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(PlayerDummy.npcName)) {
                    //restore player's position
                    gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
                    gp.player.worldY = gp.npc[gp.currentMap][i].worldY;

                    //delete the dummy
                    gp.npc[gp.currentMap][i] = null;
                    break;
                }
            }
            // draw the player again
            gp.player.drawing = true;
            // reset the values of scene, phase and return to the game
            scenePhase = NA;
            sceneNum = 0;
            gp.gameState = gp.playState;

            //set Music

            gp.playMusic(27);

        }

    }

    public void scene_ending() {
        if (scenePhase == 0) {
            gp.stopMusic();
            gp.ui.npc = new OBJ_BlueHeart(gp);
            scenePhase++;
        }
        if (scenePhase == 1) {
            //Display dialogues
            gp.ui.drawDialogueScreen();
        }
        if (scenePhase == 2) {
            gp.playSoundEffect(4);
            scenePhase++;
        }
        if (scenePhase == 3) {

            //wait until the sound effect ends
            if (counterReached(300)) {
                scenePhase++;
            }
        }
        if (scenePhase == 4) {
            // screen gets darker
            alpha += 0.005f;
            if (alpha > 1f) {
                alpha = 1f;
            }
            drawBlackBackground(alpha);
            if (alpha == 1f) {
                alpha = 0;
                scenePhase++;
            }
        }
        if (scenePhase == 5) {

            // draw a black rectangle
            drawBlackBackground(1f);

            alpha += 0.005f;
            if (alpha > 1f) {
                alpha = 1f;
            }

            String text = "After a fierce battle\n" +
                    " against the Skeleton Lord;\n" +
                    "Our hero received the King's emerald,\n" +
                    "Once part of his family treasure,\n" +
                    " until it was stolen.\n" +
                    "Now he can return home...";

            drawString(alpha, 35f, 200, text, 50);

            if (counterReached(600)) {

                gp.playMusic(0);
                scenePhase++;
            }

        }
        if(scenePhase == 6){
            drawBlackBackground(1f);

            drawString(1f, 120f, gp.screenHeight/2, "Blue boy Adventure", 40);

            if(counterReached(400)){
                scenePhase++;
            }
            
        }

        if(scenePhase == 7){
            drawBlackBackground(1f);
            y = gp.screenHeight/2;
           drawString(1f, 30f, y , endCredit, 40);
            if(counterReached(350)){
                scenePhase++;
            }
        }

        if(scenePhase == 8){
            drawBlackBackground(1f);
            
            //scroll the credits
            y--;
            drawString(1f, 30f, y, endCredit, 40);
        }
    }

    public void drawString(float alpha, float fontSize, int y, String text, int lineHeight) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.WHITE);

        g2.setFont(g2.getFont().deriveFont(fontSize));

        for (String line : text.split("\n")) {
            int x = gp.ui.getXforCenterDisplay(line);
            g2.drawString(line, x, y);
            y += lineHeight;
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

    }

    public boolean counterReached(int targetNum) {
        boolean counterReached = false;
        counter++;
        if (counter >= targetNum) {
            counterReached = true;
            counter = 0;
        }
        return counterReached;
    }

    public void drawBlackBackground(float alpha) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        //reset alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));


    }
}
