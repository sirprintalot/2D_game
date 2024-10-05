package main;


import entity.*;
import monster.*;
import objects.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
//        //KEYS
        gp.obj[0] = new OBJ_Key(gp);
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;
//
//        gp.obj[1] = new OBJ_Key(gp);
//        gp.obj[1].worldX = 33 * gp.tileSize;
//        gp.obj[1].worldY = 7 * gp.tileSize;
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
//        gp.obj[9] = new OBJ_Boots(gp);
//        gp.obj[9].worldX = 37 * gp.tileSize;
//        gp.obj[9].worldY = 42 * gp.tileSize;
//    }
}

    public void setNpc(){

        //create the first npc
        gp.npc[0] = new Npc_Old_Man(gp);
        //position on the world map
        gp.npc[0].worldX = gp.tileSize * 21;
        gp.npc[0].worldY = gp.tileSize * 20;


    }

    public void setMonster(){

        gp.monster[0] = new MON_GreenSlime(gp);
        gp.monster[0].worldX = gp.tileSize * 19;
        gp.monster[0].worldY = gp.tileSize * 36;

        gp.monster[1] = new MON_GreenSlime(gp);
        gp.monster[1].worldX = gp.tileSize * 27;
        gp.monster[1].worldY = gp.tileSize * 36;
        
    }
 }
