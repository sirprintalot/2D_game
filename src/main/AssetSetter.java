package main;


import data.*;
import entity.*;
import monster.*;
import objects.*;
import tile_interactive.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
//        //KEYS

        //OBJECTS FOT THE FIRST MAP
        int mapNum = 0;
        int objIndex = 0;
        gp.obj[mapNum][objIndex] = new OBJ_Key(gp);
        gp.obj[mapNum][objIndex].worldX = 23 * gp.tileSize;
        gp.obj[mapNum][objIndex].worldY = 7 * gp.tileSize;

        objIndex++;

        gp.obj[mapNum][objIndex] = new OBJ_Key(gp);
        gp.obj[mapNum][objIndex].worldX = 38 * gp.tileSize;
        gp.obj[mapNum][objIndex].worldY = 8 * gp.tileSize;

        objIndex++;

        gp.obj[mapNum][objIndex] = new OBJ_Boots(gp);
        gp.obj[mapNum][objIndex].worldX = 37 * gp.tileSize;
        gp.obj[mapNum][objIndex].worldY = 42 * gp.tileSize;

        objIndex++;

        gp.obj[mapNum][objIndex] = new OBJ_manaCrystal(gp);
        gp.obj[mapNum][objIndex].worldX = 25 * gp.tileSize;
        gp.obj[mapNum][objIndex].worldY = 21 * gp.tileSize;


        objIndex++;

        gp.obj[mapNum][objIndex] = new OBJ_Heart(gp);
        gp.obj[mapNum][objIndex].worldX = 28 * gp.tileSize;
        gp.obj[mapNum][objIndex].worldY = 21 * gp.tileSize;

        objIndex++;

        gp.obj[mapNum][objIndex] = new OBJ_Heart(gp);
        gp.obj[mapNum][objIndex].worldX = 29 * gp.tileSize;
        gp.obj[mapNum][objIndex].worldY = 21 * gp.tileSize;

        objIndex++;

        gp.obj[mapNum][objIndex] = new OBJ_Heart(gp);
        gp.obj[mapNum][objIndex].worldX = 30 * gp.tileSize;
        gp.obj[mapNum][objIndex].worldY = 21 * gp.tileSize;

        objIndex++;
        gp.obj[mapNum][objIndex] = new OBJ_BronzeCoin(gp);
        gp.obj[mapNum][objIndex].worldX = 27 * gp.tileSize;
        gp.obj[mapNum][objIndex].worldY = 21 * gp.tileSize;

        objIndex++;
        gp.obj[mapNum][objIndex] = new OBJ_Potion_Red(gp);
        gp.obj[mapNum][objIndex].worldX = 23 * gp.tileSize;
        gp.obj[mapNum][objIndex].worldY = 21 * gp.tileSize;


        objIndex++;
        gp.obj[mapNum][objIndex] = new OBJ_Axe(gp);
        gp.obj[mapNum][objIndex].worldX = 29 * gp.tileSize;
        gp.obj[mapNum][objIndex].worldY = 21 * gp.tileSize;

        //set door
        objIndex++;
        gp.obj[mapNum][objIndex] = new OBJ_Door(gp);
        gp.obj[mapNum][objIndex].worldX = 14 * gp.tileSize;
        gp.obj[mapNum][objIndex].worldY = 28 * gp.tileSize;

        objIndex++;
        gp.obj[mapNum][objIndex] = new OBJ_Door(gp);
        gp.obj[mapNum][objIndex].worldX = 12 * gp.tileSize;
        gp.obj[mapNum][objIndex].worldY = 12 * gp.tileSize;
//
        objIndex++;
        gp.obj[mapNum][objIndex] = new OBJ_Chest(gp);
        gp.obj[mapNum][objIndex].setLoot(new OBJ_Potion_Red(gp));
        gp.obj[mapNum][objIndex].worldX = 34 * gp.tileSize;
        gp.obj[mapNum][objIndex].worldY = 8 * gp.tileSize;

        objIndex++;
        gp.obj[mapNum][objIndex] = new OBJ_Chest(gp);
        gp.obj[mapNum][objIndex].setLoot(new OBJ_Potion_Red(gp));
        gp.obj[mapNum][objIndex].worldX = 34 * gp.tileSize;
        gp.obj[mapNum][objIndex].worldY = 9 * gp.tileSize;

        objIndex++;
        gp.obj[mapNum][objIndex] = new OBJ_Chest(gp);
        gp.obj[mapNum][objIndex].setLoot(new OBJ_manaCrystal(gp));
        gp.obj[mapNum][objIndex].worldX = 34 * gp.tileSize;
        gp.obj[mapNum][objIndex].worldY = 10 * gp.tileSize;

        objIndex++;
        gp.obj[mapNum][objIndex] = new OBJ_Lantern(gp);
        gp.obj[mapNum][objIndex].worldX = 18 * gp.tileSize;
        gp.obj[mapNum][objIndex].worldY = 20 * gp.tileSize;

        objIndex++;
        gp.obj[mapNum][objIndex] = new OBJ_Tent(gp);
        gp.obj[mapNum][objIndex].worldX = 35 * gp.tileSize;
        gp.obj[mapNum][objIndex].worldY = 35 * gp.tileSize;


        //DUNGEON   
        mapNum = 2;
        objIndex++;
        gp.obj[mapNum][objIndex] = new OBJ_Chest(gp);
        gp.obj[mapNum][objIndex].setLoot(new OBJ_PickAxe(gp));
        gp.obj[mapNum][objIndex].worldX = 40 * gp.tileSize;
        gp.obj[mapNum][objIndex].worldY = 41 * gp.tileSize;

        objIndex++;
        gp.obj[mapNum][objIndex] = new OBJ_Chest(gp);
        gp.obj[mapNum][objIndex].setLoot(new OBJ_Potion_Red(gp));
        gp.obj[mapNum][objIndex].worldX = 13 * gp.tileSize;
        gp.obj[mapNum][objIndex].worldY = 16 * gp.tileSize;

        objIndex++;
        gp.obj[mapNum][objIndex] = new OBJ_Chest(gp);
        gp.obj[mapNum][objIndex].setLoot(new OBJ_Potion_Red(gp));
        gp.obj[mapNum][objIndex].worldX = 26 * gp.tileSize;
        gp.obj[mapNum][objIndex].worldY = 34 * gp.tileSize;

        objIndex++;
        gp.obj[mapNum][objIndex] = new OBJ_Chest(gp);
        gp.obj[mapNum][objIndex].setLoot(new OBJ_Potion_Red(gp));
        gp.obj[mapNum][objIndex].worldX = 27 * gp.tileSize;
        gp.obj[mapNum][objIndex].worldY = 15 * gp.tileSize;

        // boss battle upper door
        mapNum = 3;
        objIndex = 0;
        gp.obj[mapNum][objIndex] = new OBJ_IronDoor(gp);
        gp.obj[mapNum][objIndex].worldX = 25 * gp.tileSize;
        gp.obj[mapNum][objIndex].worldY = 15 * gp.tileSize;
        objIndex++;

        gp.obj[mapNum][objIndex] = new OBJ_BlueHeart(gp);
        gp.obj[mapNum][objIndex].worldX = 25 * gp.tileSize;
        gp.obj[mapNum][objIndex].worldY = 8 * gp.tileSize;



    }

    public void setNpc() {

        int mapNum = 0;
        int i = 0;

        //create the first npc
        gp.npc[mapNum][i] = new NPC_Old_Man(gp);
        //position on the world map
        gp.npc[mapNum][i].worldX = gp.tileSize * 21;
        gp.npc[mapNum][i].worldY = gp.tileSize * 20;
        i++;


        // Merchant
        mapNum = 1;
        i = 1;
        gp.npc[mapNum][i] = new NPC_Merchant(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 12;
        gp.npc[mapNum][i].worldY = gp.tileSize * 7;

        mapNum = 2;
        i = 0;
        gp.npc[mapNum][i] = new NPC_BigRock(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 20;
        gp.npc[mapNum][i].worldY = gp.tileSize * 25;
        i++;

        gp.npc[mapNum][i] = new NPC_BigRock(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 11;
        gp.npc[mapNum][i].worldY = gp.tileSize * 18;
        i++;

        gp.npc[mapNum][i] = new NPC_BigRock(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 23;
        gp.npc[mapNum][i].worldY = gp.tileSize * 14;
        i++;


    }

    public void setMonster() {

        int mapNum = 0;
        int i = 0;

        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 19;
        gp.monster[mapNum][i].worldY = gp.tileSize * 36;

        i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 27;
        gp.monster[mapNum][i].worldY = gp.tileSize * 36;

        i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 34;
        gp.monster[mapNum][i].worldY = gp.tileSize * 42;

        i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 24;
        gp.monster[mapNum][i].worldY = gp.tileSize * 37;

        i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 38;
        gp.monster[mapNum][i].worldY = gp.tileSize * 42;

        i++;
        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 12;
        gp.monster[mapNum][i].worldY = gp.tileSize * 33;

        i++;
        gp.monster[mapNum][i] = new MON_RedSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 36;
        gp.monster[mapNum][i].worldY = gp.tileSize * 6;

        i++;
        gp.monster[mapNum][i] = new MON_RedSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 37;
        gp.monster[mapNum][i].worldY = gp.tileSize * 7;

        i++;
        gp.monster[mapNum][i] = new MON_RedSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 38;
        gp.monster[mapNum][i].worldY = gp.tileSize * 8;

        i++;
        gp.monster[mapNum][i] = new MON_RedSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 39;
        gp.monster[mapNum][i].worldY = gp.tileSize * 9;

        // Dungeon 2 pt.
        mapNum = 2;

        i++;
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 34;
        gp.monster[mapNum][i].worldY = gp.tileSize * 39;

        i++;
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 36;
        gp.monster[mapNum][i].worldY = gp.tileSize * 25;

        i++;
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 39;
        gp.monster[mapNum][i].worldY = gp.tileSize * 26;

        i++;
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 28;
        gp.monster[mapNum][i].worldY = gp.tileSize * 11;

        i++;
        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 10;
        gp.monster[mapNum][i].worldY = gp.tileSize * 19;

        mapNum = 3;
        i++;

        if(!Progress.skeletonLordDefeated){
            gp.monster[mapNum][i] = new MON_SkeletonLord(gp);
            gp.monster[mapNum][i].worldX = gp.tileSize * 23;
            gp.monster[mapNum][i].worldY = gp.tileSize * 16;
            i++;
        }


    }

    public void setInteractiveTiles() {

        int mapNum = 0;
        int i = 0;
        gp.inTile[mapNum][i] = new IT_DryTree(gp, 27, 12);
        i++;
        gp.inTile[mapNum][i] = new IT_DryTree(gp, 28, 12);
        i++;
        gp.inTile[mapNum][i] = new IT_DryTree(gp, 29, 12);
        i++;
        gp.inTile[mapNum][i] = new IT_DryTree(gp, 30, 12);
        i++;
        gp.inTile[mapNum][i] = new IT_DryTree(gp, 31, 12);
        i++;
        gp.inTile[mapNum][i] = new IT_DryTree(gp, 32, 12);
        i++;
        gp.inTile[mapNum][i] = new IT_DryTree(gp, 33, 12);
        i++;
        gp.inTile[mapNum][i] = new IT_DryTree(gp, 18, 40);
        i++;
        gp.inTile[mapNum][i] = new IT_DryTree(gp, 17, 40);
        i++;
        gp.inTile[mapNum][i] = new IT_DryTree(gp, 16, 40);
        i++;
        gp.inTile[mapNum][i] = new IT_DryTree(gp, 15, 40);
        i++;
        gp.inTile[mapNum][i] = new IT_DryTree(gp, 14, 40);
        i++;
        gp.inTile[mapNum][i] = new IT_DryTree(gp, 13, 40);
        i++;
        gp.inTile[mapNum][i] = new IT_DryTree(gp, 13, 41);
        i++;
        gp.inTile[mapNum][i] = new IT_DryTree(gp, 12, 41);
        i++;
        gp.inTile[mapNum][i] = new IT_DryTree(gp, 11, 41);
        i++;
        gp.inTile[mapNum][i] = new IT_DryTree(gp, 10, 41);
        i++;
        gp.inTile[mapNum][i] = new IT_DryTree(gp, 10, 40);

        //DUNGEON   
        mapNum = 2;
        i = 0;
        //Destructible wall
//        gp.inTile[mapNum][i] = new IT_DestructibleWall(gp, 18, 30);
//        i++;
//        gp.inTile[mapNum][i] = new IT_DestructibleWall(gp, 17, 31);
//        i++;
//        gp.inTile[mapNum][i] = new IT_DestructibleWall(gp, 17, 32);
//        i++;
//        gp.inTile[mapNum][i] = new IT_DestructibleWall(gp, 17, 34);
//        i++;
//        gp.inTile[mapNum][i] = new IT_DestructibleWall(gp, 18, 34);
//        i++;
//        gp.inTile[mapNum][i] = new IT_DestructibleWall(gp, 18, 33);
//        i++;
//        gp.inTile[mapNum][i] = new IT_DestructibleWall(gp, 10, 22);
//        i++;
//        gp.inTile[mapNum][i] = new IT_DestructibleWall(gp, 10, 24);
//        i++;
//        gp.inTile[mapNum][i] = new IT_DestructibleWall(gp, 38, 18);
//        i++;
//        gp.inTile[mapNum][i] = new IT_DestructibleWall(gp, 38, 19);
//        i++;
//        gp.inTile[mapNum][i] = new IT_DestructibleWall(gp, 38, 20);
//        i++;
//        gp.inTile[mapNum][i] = new IT_DestructibleWall(gp, 38, 21);
//        i++;
//        gp.inTile[mapNum][i] = new IT_DestructibleWall(gp, 18, 13);
//        i++;
//        gp.inTile[mapNum][i] = new IT_DestructibleWall(gp, 18, 14);
//        i++;
//        gp.inTile[mapNum][i] = new IT_DestructibleWall(gp, 22, 28);
//        i++;
//        gp.inTile[mapNum][i] = new IT_DestructibleWall(gp, 30, 28);
//        i++;
//        gp.inTile[mapNum][i] = new IT_DestructibleWall(gp, 32, 28);
//        i++;
//
//        //Metal plate
//        gp.inTile[mapNum][i] = new IT_MetalPLate(gp, 20, 22);
//        i++;
//        gp.inTile[mapNum][i] = new IT_MetalPLate(gp, 8, 17);
//        i++;
//        gp.inTile[mapNum][i] = new IT_MetalPLate(gp, 39, 31);
//        i++;

    }


}
