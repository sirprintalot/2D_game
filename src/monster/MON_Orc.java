package monster;

import entity.*;
import main.*;
import objects.*;

import java.util.*;

public class MON_Orc extends Entity {


    GamePanel gp;

    public MON_Orc(GamePanel gp) {

        super(gp);
        this.gp = gp;

        type = typeMonster;
        name = "Orc";
        defaultSpeed = 3;
        speed = defaultSpeed;
        maxLife = 10;
        life = maxLife;

        attack = 8;
        defense = 2;
        exp = 10;

        solidArea.x = 3;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 44;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 48;
        attackArea.height = 48;

        getImage();
        getAttackImage();

    }

    public void getImage() {

        up1 = setup("/monster/orc_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/orc_up_2", gp.tileSize, gp.tileSize);

        down1 = setup("/monster/orc_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/orc_down_2", gp.tileSize, gp.tileSize);

        left1 = setup("/monster/orc_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/orc_left_2", gp.tileSize, gp.tileSize);

        right1 = setup("/monster/orc_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/orc_right_2", gp.tileSize, gp.tileSize);

    }

    public void getAttackImage(){
        attackUp1 = setup("/monster/orc_attack_up_1", gp.tileSize, gp.tileSize * 2);
        attackUp2 = setup("/monster/orc_attack_up_2", gp.tileSize, gp.tileSize * 2);
        attackDown1 = setup("/monster/orc_attack_down_1", gp.tileSize, gp.tileSize * 2);
        attackDown2 = setup("/monster/orc_attack_down_2", gp.tileSize, gp.tileSize * 2);
        attackLeft1 = setup("/monster/orc_attack_left_1", gp.tileSize * 2, gp.tileSize);
        attackLeft2 = setup("/monster/orc_attack_left_2", gp.tileSize * 2, gp.tileSize);
        attackRight1 = setup("/monster/orc_attack_right_1", gp.tileSize * 2, gp.tileSize);
        attackRight2 = setup("/monster/orc_attack_right_2", gp.tileSize * 2, gp.tileSize);
    }

    // case 3 NPC getting aggro at certain distance
//    public void update(){
//        super.update();
//    }

    public void setAction() {

        if (onPath) {
            //check if stops chasing
            //stop hunting at a certain distance
//        if(tileDistance >= 10){
//            onPath = false;
//            System.out.println("aggro finished!");
//        }

            //search direction to goal
            // case 2 the npc follows the player
//            speed = 7;
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));

        } else {
            //check if starts chasing
//            checkStopChasing(gp.player, 15, 100);


            //checkStartChasing(gp.player, 5, 100);
            //if(!onPath && tileDistance < 5){
//            // option 1
//            // onPath = true;
//
//            //option 2 randomize
//            int i = new Random().nextInt(100) + 1;
//            if(i > 50){
//                onPath = true;
//                System.out.println("aggro started ");
//            }
//        }
            //get a random direction
            getRandomDirection();


        }

    }

    public void damageReaction() {

        actionLockCounter = 0;
//        direction = gp.player.direction;
        //getting aggro when player attacks
        onPath = true;

    }

    public void checkItemDrop() {

        //pick a random number
        int i = new Random().nextInt(100) + 1;

        //set the monster drop
        if (i < 50) {
            dropItem(new OBJ_BronzeCoin(gp));
        }
        if (i >= 50 && i < 75) {
            dropItem(new OBJ_Heart(gp));
        }
        if (i >= 75 && i < 100) {

            dropItem(new OBJ_manaCrystal(gp));
        }

    }

}

