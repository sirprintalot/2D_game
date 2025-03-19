package main;


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
        gp.obj[mapNum][objIndex].worldY = 12  * gp.tileSize;
//
        objIndex++;
        gp.obj[mapNum][objIndex] = new OBJ_Chest(gp);
        gp.obj[mapNum][objIndex].setLoot(new OBJ_Potion_Red(gp));
        gp.obj[mapNum][objIndex].worldX = 34 * gp.tileSize;
        gp.obj[mapNum][objIndex].worldY = 8  * gp.tileSize;

        objIndex++;
        gp.obj[mapNum][objIndex] = new OBJ_Chest(gp );
        gp.obj[mapNum][objIndex].setLoot(new OBJ_Potion_Red(gp));
        gp.obj[mapNum][objIndex].worldX = 34 * gp.tileSize;
        gp.obj[mapNum][objIndex].worldY = 9  * gp.tileSize;

        objIndex++;
        gp.obj[mapNum][objIndex] = new OBJ_Chest(gp);
        gp.obj[mapNum][objIndex].setLoot(new OBJ_manaCrystal(gp));
        gp.obj[mapNum][objIndex].worldX = 34 * gp.tileSize;
        gp.obj[mapNum][objIndex].worldY = 10  * gp.tileSize;

        objIndex++;
        gp.obj[mapNum][objIndex] = new OBJ_Lantern(gp);
        gp.obj[mapNum][objIndex].worldX = 18 * gp.tileSize;
        gp.obj[mapNum][objIndex].worldY = 20  * gp.tileSize;

        objIndex++;
        gp.obj[mapNum][objIndex] = new OBJ_Tent(gp);
        gp.obj[mapNum][objIndex].worldX = 35 * gp.tileSize;
        gp.obj[mapNum][objIndex].worldY = 35  * gp.tileSize;
        
    }

    public void setNpc() {

        int mapNum = 0;
        int i = 0;

        //create the first npc
        gp.npc[mapNum][i] = new NPC_Old_Man(gp);
        //position on the world map
        gp.npc[mapNum][i].worldX = gp.tileSize * 21;
        gp.npc[mapNum][i].worldY = gp.tileSize * 20;


        // Merchant
         mapNum = 1;
         i = 1;
        gp.npc[mapNum][i] = new NPC_Merchant(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 12;
        gp.npc[mapNum][i].worldY = gp.tileSize * 7;



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
//        gp.inTile[mapNum][i] = new IT_DryTree(gp, 21, 21);
//        i++;
        //new
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

    }


}
