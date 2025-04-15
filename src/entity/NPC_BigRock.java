package entity;

import main.*;

import java.util.*;

public class NPC_BigRock extends Entity {

    public static final String npcName = "Big Rock";

    //Constructor
    public NPC_BigRock(GamePanel gp) {

        super(gp);
        name = npcName;
        direction = "down";
        speed = 2;
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
            dialogueSet --;
        }
    }

    public void update(){}

    public void move(String d){
        this.direction = d;

        checkCollision();
        if(!collisionOn){
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
            }
        }
    }

}
