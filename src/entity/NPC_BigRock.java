package entity;

import main.*;
import objects.*;
import tile_interactive.*;

import java.awt.*;
import java.util.*;

public class NPC_BigRock extends Entity {

    public static final String npcName = "Big Rock";

    //Constructor
    public NPC_BigRock(GamePanel gp) {

        super(gp);
        name = npcName;
        direction = "down";
        speed = 4;
//        solidArea = new Rectangle();
        solidArea.x = 2;
        solidArea.y = 6;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 44;
        solidArea.height = 40;

        dialogueSet = -1;

        getImage();
        setDialogue();


    }

    //Get the sprite image depending on the position
    public void getImage() {
        //enhanced method
        up1 = setup("/npc/bigrock", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/bigrock", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/bigrock", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/bigrock", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/bigrock", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/bigrock", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/bigrock", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/bigrock", gp.tileSize, gp.tileSize);

    }

    //Load dialogues
    public void setDialogue() {

        dialogues[0][0] = "A giant Rock..";
    }


    public void speak() {
        facePlayer();
        startDialogue(this, dialogueSet);

        dialogueSet++;

        if (dialogues[dialogueSet][0] == null) {
            dialogueSet--;
        }
    }

    public void update() {
    }

    public void move(String d) {
        this.direction = d;

        checkCollision();
        if (!collisionOn) {
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
            }
        }
        detectPlate();
    }

    public void detectPlate() {

        ArrayList<InteractiveTile> plateList = new ArrayList<>();
        ArrayList<Entity> rockList = new ArrayList<>();

        //create a plate list
        for (int i = 0; i < gp.inTile[gp.currentMap].length; i++) {
            if (gp.inTile[gp.currentMap][i] != null && gp.inTile[gp.currentMap][i]instanceof IT_MetalPLate) {
                plateList.add(gp.inTile[gp.currentMap][i]);
            }
        }

        // rock list
        for (int i = 0; i < gp.npc[1].length; i++) {
            if (gp.npc[gp.currentMap][i] != null &&
                    gp.npc[gp.currentMap][i].name.equals(NPC_BigRock.npcName)) {
                rockList.add(gp.npc[gp.currentMap][i]);
            }
        }

        int rockCounter = 0;
        
        //scan the plate list
        for (int i = 0; i < plateList.size(); i++) {
            int xDistance = Math.abs(worldX - plateList.get(i).worldX);
            int yDistance = Math.abs(worldY - plateList.get(i).worldY);
            int distance = Math.max(xDistance, yDistance);

            int minDistance = 8;

            if (distance < minDistance) {
                if (linkedEntity == null) {
                    linkedEntity = plateList.get(i);
                    gp.playSoundEffect(26);

                }
            } else {
                if (linkedEntity == plateList.get(i)) {
                    linkedEntity = null;
                }

            }
        }

        //scan the rock list
        for(int i = 0; i < rockList.size(); i++){
            // count the rock on the plate
            if(rockList.get(i).linkedEntity != null){
                rockCounter++;
            }
        }

        // Unlock the door when all 3 rock are in the metal plates
        if(rockCounter == rockList.size()){
            for(int i = 0; i < gp.obj[1].length; i++){
                if(gp.obj[gp.currentMap][i] != null && gp.obj[gp.currentMap][i].name.equals(OBJ_IronDoor.objName)){
                    gp.obj[gp.currentMap][i] = null;
                    gp.playSoundEffect(24);
                    gp.ui.addMessage("Something opened...", Color.DARK_GRAY);
                }
            }
        }


    }

}
