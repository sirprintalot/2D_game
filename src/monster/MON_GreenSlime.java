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
        speed = 4;
        maxLife = 5;
        life = maxLife;

        attack = 4;
        defense = 1;
        exp = 2;

        projectile = new OBJ_Rock(gp);

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
//
//        //distance to the player
//        int xDistance = Math.abs(worldX - gp.player.worldX);
//        int yDistance = Math.abs(worldY - gp.player.worldY);
//        int tileDistance = (xDistance + yDistance) / gp.tileSize;
//
//        if(!onPath && tileDistance < 5){
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
//        //stop hunting at a certain distance
//        if(onPath && tileDistance >= 10){
//            onPath = false;
//            System.out.println("aggro finished!");
//        }
//
//    }

    public void setAction() {

        if(onPath){
            // case 2 the npc follows the player
            int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;;
//            speed = 7;
            searchPath(goalCol, goalRow);

            int i = new Random().nextInt(100) + 1;
            if(i > 99 && !projectile.isAlive && shotAvailableCounter == 30){
                projectile.set(worldX, worldY, direction, true, this);
                gp.projectileList.add(projectile);
                shotAvailableCounter = 0;
            }
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

    public void damageReaction(){

        actionLockCounter = 0;
//        direction = gp.player.direction;
        //getting aggro when player attacks
        onPath = true;
        
    }

    public void checkItemDrop(){

        //pick a random number
        int i = new Random().nextInt(100) + 1;
        
        //set the monster drop
        if(i < 50){
            dropItem(new OBJ_BronzeCoin(gp));
        }
        if(i >= 50 && i < 75){
            dropItem(new OBJ_Heart(gp));
        }
        if(i >= 75 && i < 100){

            dropItem(new OBJ_manaCrystal(gp));
        }

    }

}
