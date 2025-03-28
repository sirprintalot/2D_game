package monster;

import entity.*;
import main.*;
import objects.*;

import java.util.*;

public class MON_RedSlime extends Entity {

    GamePanel gp;

    public MON_RedSlime(GamePanel gp) {

        super(gp);
        this.gp = gp;

        type = typeMonster;
        name = "Red Slime";
        defaultSpeed = 5;
        speed = defaultSpeed;
        maxLife = 8;
        life = maxLife;
        knockBackPower = 3;

        attack = 5;
        defense = 3;
        exp = 6;

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

        up1 = setup("/monster/redslime_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/redslime_down_2", gp.tileSize, gp.tileSize);

        down1 = setup("/monster/redslime_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/redslime_down_2", gp.tileSize, gp.tileSize);

        left1 = setup("/monster/redslime_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/redslime_down_2", gp.tileSize, gp.tileSize);

        right1 = setup("/monster/redslime_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/redslime_down_2", gp.tileSize, gp.tileSize);

    }

    // case 3 NPC getting aggro at certain distance
    public void update(){
        super.update();
    }

    public void setAction() {

        if (onPath) {
            //stop hunting at a certain distance
        if(this.getTileDistance(gp.player) >= 15){
            onPath = false;
        }
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
            //check if it shoots a projectile
            checkShoot(200, 30);
        } else {
            //get a random direction
            checkStopChasing(gp.player, 15, 100);
            checkStartChasing(gp.player, 5, 100);

            getRandomDirection();
        }
    }

    public void damageReaction() {

        actionLockCounter = 0;

    }

    public void checkItemDrop() {

        //pick a random number
        int i = new Random().nextInt(100) + 1;

        //set the monster drop
        if (i < 50) {
            dropItem(new OBJ_Tent(gp));
        }
        if (i >= 50 && i < 75) {
            dropItem(new OBJ_Boots(gp));
        }
        if (i >= 75 && i < 100) {

            dropItem(new OBJ_Lantern(gp));
        }

    }

}
