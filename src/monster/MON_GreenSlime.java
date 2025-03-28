package monster;

import entity.*;
import main.*;
import objects.*;

import java.util.*;

public class MON_GreenSlime extends Entity {

    GamePanel gp;

    public MON_GreenSlime(GamePanel gp) {

        super(gp);
        this.gp = gp;

        type = typeMonster;
        name = "Green Slime";
        defaultSpeed = 3;
        speed = defaultSpeed;
        maxLife = 6;
        life = maxLife;
        knockBackPower = 1;

        attack = 4;
        defense = 2;
        exp = 2;

//        projectile = new OBJ_Rock(gp);

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 35;
        solidArea.height = 35;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();

    }

    public void getImage() {

        up1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);

        down1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);

        left1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);

        right1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);

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
            //check if it shoots a projectile
//            checkShoot(200, 30);
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
