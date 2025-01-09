package entity;

import main.*;

import java.util.*;

public class NPC_Old_Man extends Entity {


    //Constructor
    public NPC_Old_Man(GamePanel gp) {

        super(gp);
        direction = "up";
        speed = 2;
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

    //Load dialogues
    public void setDialogue(){

        dialogues[0] = "Caco te ofrece coca machucada... /n Aceptar /n Rechazar";
        dialogues[1] = "akfbakfbakfbak";
        dialogues[2] = "sdad jasdfkanfn asdasdfa dafafasf /n qsaasasdasdfaf";
        dialogues[3] = "asdfasf aksfbajffopnfasdfafaf /n asfafsasdasdad /n asdasdfasf";
    }

    public void speak(){

        super.speak();
    }

}
