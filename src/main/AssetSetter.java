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

        int objIndex = 0;
        gp.obj[objIndex] = new OBJ_Key(gp);
        gp.obj[objIndex].worldX = 23 * gp.tileSize;
        gp.obj[objIndex].worldY = 7 * gp.tileSize;

        objIndex++;

        gp.obj[objIndex] = new OBJ_Key(gp);
        gp.obj[objIndex].worldX = 38 * gp.tileSize;
        gp.obj[objIndex].worldY = 8 * gp.tileSize;

        objIndex++;

        gp.obj[objIndex] = new OBJ_Boots(gp);
        gp.obj[objIndex].worldX = 37 * gp.tileSize;
        gp.obj[objIndex].worldY = 42 * gp.tileSize;

        objIndex++;

        gp.obj[objIndex] = new OBJ_manaCrystal(gp);
        gp.obj[objIndex].worldX = 25 * gp.tileSize;
        gp.obj[objIndex].worldY = 21 * gp.tileSize;


        objIndex++;

        gp.obj[objIndex] = new OBJ_Heart(gp);
        gp.obj[objIndex].worldX = 27 * gp.tileSize;
        gp.obj[objIndex].worldY = 21 * gp.tileSize;

        objIndex++;
        gp.obj[objIndex] = new OBJ_BronzeCoin(gp);
        gp.obj[objIndex].worldX = 27 * gp.tileSize;
        gp.obj[objIndex].worldY = 21 * gp.tileSize;

        objIndex++;
        gp.obj[objIndex] = new OBJ_Potion_Red(gp);
        gp.obj[objIndex].worldX = 23 * gp.tileSize;
        gp.obj[objIndex].worldY = 21 * gp.tileSize;


        gp.obj[1] = new OBJ_Axe(gp);
        gp.obj[1].worldX = 29 * gp.tileSize;
        gp.obj[1].worldY = 21 * gp.tileSize;
////
//        gp.obj[2] = new OBJ_Key(gp);
//        gp.obj[2].worldX = 35 * gp.tileSize;
//        gp.obj[2].worldY = 43 * gp.tileSize;
//
//        //DOORS
//        gp.obj[3] = new OBJ_Door(gp);
//        gp.obj[3].worldX = 23 * gp.tileSize;
//        gp.obj[3].worldY = 30 * gp.tileSize;
//
//        gp.obj[4] = new OBJ_Door(gp);
//        gp.obj[4].worldX = 12 * gp.tileSize;
//        gp.obj[4].worldY = 22 * gp.tileSize;
//
//        gp.obj[5] = new OBJ_Door(gp);
//        gp.obj[5].worldX = 23 * gp.tileSize;
//        gp.obj[5].worldY = 35 * gp.tileSize;
//
//        //CHESTS
//        gp.obj[6] = new OBJ_Chest(gp);
//        gp.obj[6].worldX = 11 * gp.tileSize;
//        gp.obj[6].worldY = 34 * gp.tileSize;
//
//        gp.obj[7] = new OBJ_Chest(gp);
//        gp.obj[7].worldX = 10 * gp.tileSize;
//        gp.obj[7].worldY = 7 * gp.tileSize;
//
//        gp.obj[8] = new OBJ_Chest(gp);
//        gp.obj[8].worldX = 20 * gp.tileSize;
//        gp.obj[8].worldY = 42 * gp.tileSize;
//

//    }
    }

    public void setNpc() {

        //create the first npc
        gp.npc[0] = new Npc_Old_Man(gp);
        //position on the world map
        gp.npc[0].worldX = gp.tileSize * 21;
        gp.npc[0].worldY = gp.tileSize * 20;


    }

    public void setMonster() {

        int i = 0;

        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 19;
        gp.monster[i].worldY = gp.tileSize * 36;

        i++;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 27;
        gp.monster[i].worldY = gp.tileSize * 36;

        i++;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 34;
        gp.monster[i].worldY = gp.tileSize * 42;

        i++;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 24;
        gp.monster[i].worldY = gp.tileSize * 37;

        i++;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 38;
        gp.monster[i].worldY = gp.tileSize * 42;

    }

    public void setInteractiveTiles() {

        int i = 0;
        gp.inTile[i] = new IT_DryTree(gp, 27, 12);
        i++;

        gp.inTile[i] = new IT_DryTree(gp, 28, 12);
        i++;

        gp.inTile[i] = new IT_DryTree(gp, 29, 12);
        i++;

        gp.inTile[i] = new IT_DryTree(gp, 30, 12);
        i++;

        gp.inTile[i] = new IT_DryTree(gp, 31, 12);
        i++;

        gp.inTile[i] = new IT_DryTree(gp, 32, 12);
        i++;

        gp.inTile[i] = new IT_DryTree(gp, 33, 12);
        i++;
        gp.inTile[i] = new IT_DryTree(gp, 21, 21);
        i++;
        //new
        gp.inTile[i] = new IT_DryTree(gp, 18, 40);
        i++;
        gp.inTile[i] = new IT_DryTree(gp, 17, 40);
        i++;
        gp.inTile[i] = new IT_DryTree(gp, 16, 40);
        i++;
        gp.inTile[i] = new IT_DryTree(gp, 15, 40);
        i++;
        gp.inTile[i] = new IT_DryTree(gp, 14, 40);
        i++;
        gp.inTile[i] = new IT_DryTree(gp, 13, 40);
        i++;
        gp.inTile[i] = new IT_DryTree(gp, 13, 41);
        i++;
        gp.inTile[i] = new IT_DryTree(gp, 12, 41);
        i++;
        gp.inTile[i] = new IT_DryTree(gp, 11, 41);
        i++;
        gp.inTile[i] = new IT_DryTree(gp, 10, 41);
        i++;
        gp.inTile[i] = new IT_DryTree(gp, 10, 40);

    }


}
