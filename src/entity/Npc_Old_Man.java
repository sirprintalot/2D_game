package entity;

import main.*;

import java.awt.*;
import java.util.*;

public class NPC_Old_Man extends Entity {


    //Constructor
    public NPC_Old_Man(GamePanel gp) {

        super(gp);
        direction = "down";
        speed = 2;
//        solidArea = new Rectangle();
        solidArea.x = 9;
        solidArea.y = 7;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 30;
        solidArea.height = 30;

        dialogueSet = -1;

        getImage();
        setDialogue();


    }

    //Get the sprite image depending on the position
    public void getImage() {
        //enhanced method
        up1 = setup("/npc/oldman_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/oldman_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/oldman_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/oldman_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/oldman_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/oldman_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/oldman_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/oldman_right_2", gp.tileSize, gp.tileSize);
    }

    //Define a random movement with a set delay
    public void setAction() {

        if(onPath){

            // case 1get the npc to a specific location
//            int goalCol = 12;
//            int goalRow = 8;

            // case 2 the npc follows the player
            int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;;
            speed = 3;

            searchPath(goalCol, goalRow);

        }

        else{
            actionLockCounter++;

            if (actionLockCounter == 120) {

                Random rand = new Random();
                int i = rand.nextInt(100) + 1;

                if (i <= 25) {
                    direction = "up";
                }
                if (i > 26 && i <= 50) {
                    direction = "down";
                }
                if (i > 51 && i <= 75) {
                    direction = "left";
                }
                if (i > 76) {
                    direction = "right";
                }
                actionLockCounter = 0;
            }
        }
    }

    //Load dialogues
    public void setDialogue() {

        // DIALOGUE SET 1
        dialogues[0][0] = "Ah, traveler.." +
                "#You have the look" +
                "#of someone seeking wisdom." +
                "#How may I assist you?";
        
        dialogues[0][1] = "Patience, dear one. " +
                "#Rushing into danger without thought " +
                "#is the surest way to regret.";
        dialogues[0][2] = "There, there. " +
                "#Rest now, child. " +
                "#Even the strongest warriors " +
                "#need a gentle hand.";

        // DIALOGUE SET 2
        dialogues[1][0] = "Long ago, I lost something dear… " +
                "#If you happen upon it in your travels, " +
                "#I would be most " +
                "grateful.";
        dialogues[1][1] = "You carry a heavy burden, " +
                "#but do not falter. " +
                "#Even the smallest light can push back the " +
                "#darkness.";


        //DIALOGUE SET 3
        dialogues[2][0] =  "Go forth, " +
                "#and may the winds of fortune " +
                "#guide your path.";

        //DIALOGUE SET 4
        dialogues[3][0] =  "Your health is low.." +
                "#You should take a rest";
    }


    public void speak() {
        facePlayer();
        startDialogue(this, dialogueSet);

        dialogueSet++; // this change de dialogue set everytime we speak with the npc
       // onPath = true;

        // use a specific dialogue set based on specific conditions.
        if(gp.player.life == gp.player.maxLife/3){
            dialogueSet = 3;
        }

        if(dialogues[dialogueSet][0] == null) {
            dialogueSet = 0;
            //dialogueSet--; repeat the las dialogue
        }
    }
}
