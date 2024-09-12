package entity;

import main.*;

import java.util.*;

public class Npc_Old_Man extends Entity {


    public Npc_Old_Man(GamePanel gp) {

        super(gp);
        direction = "up";
        speed = 1;
        getImage();
        setDialogue();

    }

    public void getImage() {
        //enhanced method
        up1 = setup("/npc/oldman_up_1");
        up2 = setup("/npc/oldman_up_2");
        down1 = setup("/npc/oldman_down_1");
        down2 = setup("/npc/oldman_down_2");
        left1 = setup("/npc/oldman_left_1");
        left2 = setup("/npc/oldman_left_2");
        right1 = setup("/npc/oldman_right_1");
        right2 = setup("/npc/oldman_right_2");
    }

    public void setAction() {

        actionLockCounter++;

        if (actionLockCounter == 120) {

            Random rand = new Random();
            int i = rand.nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75) {
                direction = "right";
            }

            actionLockCounter = 0;
        }

    }

    public void setDialogue(){

        dialogues[0] = "hello";
        dialogues[1] = "akfbakfbakfbak";
        dialogues[2] = "sdad jasdfkanfn";
        dialogues[3] = "asdfasf aksfbajffopnfd 1111";
    }

    public void speak(){

        //if all the dialogues are finished, loop back to the first one
        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }

        gp.ui.currentDialog = dialogues[dialogueIndex];
        dialogueIndex++;

        //turn the npc facing the player when a dialogue occurs
        switch (gp.player.direction) {
            case "up" -> direction = "down";
            case "down" -> direction = "up";
            case "left" -> direction = "right";
            case "right" -> direction = "left";
        }
    }
}
