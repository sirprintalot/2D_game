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

    //Scene number
    public final int NA = 0;
    public final int skeletonLord = 1;

    public CutSceneManager(GamePanel gp){
        this.gp = gp;
        
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;

        // for multiple cutScenes
        switch (sceneNum){
            case skeletonLord: scene_skeletonLord(); break;

        }

    }

    public void scene_skeletonLord(){

        // freeze the boss and the player and close the doors
        if(scenePhase == 0){
            gp.bossBattleOn = true;

            // close the iron gate
            for(int i = 0; i < gp.obj[1].length; i++){
                if(gp.obj[gp.currentMap][i] == null){
                    gp.obj[gp.currentMap][i] = new OBJ_IronDoor(gp);
                    gp.obj[gp.currentMap][i].worldX = gp.tileSize * 25;
                    gp.obj[gp.currentMap][i].worldY = gp.tileSize * 28;
                    gp.obj[gp.currentMap][i].temp = true;
                    gp.playSoundEffect(26);
                    break;
                }
            }

            // search an empty slot on the npc array to put the dummy
            for(int i = 0; i < gp.npc[1].length; i++){
                if(gp.npc[gp.currentMap][i] == null){
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
        if(scenePhase == 1){
            gp.player.worldY -= 2;
            
            if(gp.player.worldY < gp.tileSize* 19) {
                scenePhase++;
            }
        }

        // Unfreeze the boss
        if(scenePhase == 2){
            //search the boss on the monster array
            for(int i = 0; i < gp.monster[1].length; i++){
                if(gp.monster[gp.currentMap][i] != null && Objects.equals(gp.monster[gp.currentMap][i].name, MON_SkeletonLord.monName)){
                    gp.monster[gp.currentMap][i].sleep = false;

                    // make the boss talk
                    gp.ui.npc = gp.monster[gp.currentMap][i];
                    scenePhase++;
                    break;
                }
            }
        }

        //Boss speak
        if(scenePhase  == 3){
                gp.stopMusic();
              gp.ui.drawDialogueScreen();
        }

        // Return the camera to the player
        if(scenePhase == 4){
            //search and eliminate the dummy
            for(int i = 0; i < gp.npc[1].length; i++){
                if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(PlayerDummy.npcName)){
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



}
