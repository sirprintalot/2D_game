package main;


import data.*;
import entity.*;

public class EventHandler {

    GamePanel gp;
    Entity eventMaster;
    EventRect[][][] eventRect;

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    //Transitions
    int tempMap, tempRow, tempCol;


    //TODO check this method

    public EventHandler(GamePanel gp) {

        this.gp = gp;
        eventMaster = new Entity(gp);
        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        int map = 0;
        int row = 0;
        int col = 0;

        while (map < gp.maxMap && row < gp.maxWorldRow && col < gp.maxWorldCol) {
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
                if (row == gp.maxWorldRow) {
                    row = 0;
                    map++;
                }
            }
        }

        setDialogue();
    }

    public void setDialogue() {
        eventMaster.dialogues[0][0] = "You fall into a pit";

        eventMaster.dialogues[1][0] = "You drank the healing water. /nLife and Mana restored " +
                "/n(Progress saved!)";

        eventMaster.dialogues[2][0] = "Teleported!!";
    }

    public void checkEvent() {
        //Check the distance between the character, and the event tile
        //is it's more than a tile the event can happen again
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);

        if (distance > gp.tileSize) {
            canTouchEvent = true;
        }

        if (canTouchEvent) {

            if (hit(0, 27, 16, "right")) {
                damagePit(gp.dialogueState);
            } else if (hit(0, 23, 42, "any")) {
                teleport(gp.dialogueState);
            } else if (hit(0, 23, 12, "any")) {
                healingPool(gp.dialogueState);
            }


            //Move to the Merchant map
            else if (hit(0, 10, 39, "any")) {
                nextMap(1, 12, 13, gp.indoor);
                gp.stopMusic();
            }
            //Go back to the original map
            else if (hit(1, 12, 13, "any")) {
                nextMap(0, 10, 39, gp.outside);
                //speak to the merchant
            } else if (hit(1, 12, 9, "up")) {
                speak(gp.npc[1][1]);
                // go into the dungeon
            } else if (hit(0,12,9, "any")) {
                nextMap(2,9, 41, gp.dungeon );
            }
            //get of the dungeon
            else if (hit(2,9,41, "any")) {
                nextMap(0,11, 9, gp.outside );
            }
            //get into the dungeon 2
            else if (hit(2,8,7, "any")) {
                nextMap(3,26, 39, gp.dungeon );
            }
            //get out of the dungeon 2
            else if (hit(3,26,40, "any")) {
                nextMap(2,8, 7, gp.dungeon );
            }

            //Boss battle
            else if (hit(3,25,27, "any")) {
                skeletonLord();
            }
        }

    }

    //TODO check this method again
    public boolean hit(int map, int col, int row, String reqDirection) {

        boolean hit = false;

        if (map == gp.currentMap) {
            // PLayer's position
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;

            //check if there's a collision
            if (gp.player.solidArea.intersects(eventRect[map][col][row]) && !eventRect[map][col][row].eventDone) {
                if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {

                    hit = true;

                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }

            //reset positions
            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;

            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }

        return hit;
    }

    public void damagePit(int gameState) {

        gp.gameState = gameState;
        gp.playSoundEffect(10);
        eventMaster.startDialogue(eventMaster, 0);
        gp.player.life -= 1;

        //this flag makes the event happens just one time
//        eventRect[col][row].eventDone = true;
        //reset the tile distace to the event so will keep happening
        //as long you are at one tile of distance
        canTouchEvent = false;
    }

    public void healingPool(int gameState) {

        if (gp.keyH.enterPressed) {
            gp.gameState = gameState;
            gp.player.attackCancel = true;
            gp.playSoundEffect(2);
            eventMaster.startDialogue(eventMaster, 1);
            gp.player.life = gp.player.maxLife;
            canTouchEvent = false;
            //restore mana
            gp.player.mana = gp.player.maxMana;
            //after healing, the monsters reappear
            gp.assetSetter.setMonster();

            // save the game when using the healing pool
            gp.saveLoad.save();
        }
    }

    public void teleport(int gameState) {

        gp.gameState = gameState;
        gp.playSoundEffect(12);

        eventMaster.startDialogue(eventMaster, 2);

        // position to be teleported to
        gp.player.worldX = gp.tileSize * 38;
        gp.player.worldY = gp.tileSize * 7;

        //TODO after one teleportation reset the tile to the original one
        //TODO MAKE THE PLAYER flicker while teleporting
        //TODO set dialogue
    }

    public void nextMap(int map, int col, int row, int area) {

        gp.gameState = gp.transitionState;
        //Transition
        tempMap = map;
        tempCol = col;
        tempRow = row;

        gp.nextArea = area;

//        gp.currentMap = map;
//        gp.player.worldX = gp.tileSize * col;
//        gp.player.worldY = gp.tileSize * row;
//        previousEventX = gp.player.worldX;
//        previousEventY = gp.player.worldY;

        canTouchEvent = false;
        gp.playSoundEffect(18);
//        gp.eManager.lighting.resetDay();

    }

    public void speak(Entity entity) {
        if (gp.keyH.enterPressed) {
            gp.gameState = gp.dialogueState;
            gp.player.attackCancel = true;
            entity.speak();
        }
    }

    public void skeletonLord(){
          if(!gp.bossBattleOn && !Progress.skeletonLordDefeated){
              gp.gameState = gp.cutSceneState;
              gp.cutSceneManager.sceneNum = gp.cutSceneManager.skeletonLord; 
          }
    }


}