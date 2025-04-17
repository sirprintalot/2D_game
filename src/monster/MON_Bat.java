package monster;

import entity.*;
import main.*;
import objects.*;

import java.util.*;

public class MON_Bat extends Entity {

    GamePanel gp;

    public MON_Bat(GamePanel gp) {

        super(gp);
        this.gp = gp;

        type = typeMonster;
        name = "Bat";
        defaultSpeed = 4;
        speed = defaultSpeed;
        maxLife = 7;
        life = maxLife;
        knockBackPower = 4;

        attack = 4;
        defense = 2;
        exp = 6;

//        projectile = new OBJ_Rock(gp);

        solidArea.x = 3;
        solidArea.y = 15;
        solidArea.width = 42;
        solidArea.height = 21;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();

    }

    public void getImage() {

        up1 = setup("/monster/bat_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/bat_down_2", gp.tileSize, gp.tileSize);

        down1 = setup("/monster/bat_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/bat_down_2", gp.tileSize, gp.tileSize);

        left1 = setup("/monster/bat_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/bat_down_2", gp.tileSize, gp.tileSize);

        right1 = setup("/monster/bat_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/bat_down_2", gp.tileSize, gp.tileSize);

    }

    // case 3 NPC getting aggro at certain distance
//    public void update(){
//        super.update();
//    }

    public void setAction() {
        
            getRandomDirection(10);

    }

    public void damageReaction() {

        actionLockCounter = 0;
//        onPath = true;

    }

    public void checkItemDrop() {

        //pick a random number
        int i = new Random().nextInt(50) + 1;

        //set the monster drop
        if (i < 25) {
            dropItem(new OBJ_BronzeCoin(gp));
        }
        if (i >= 25) {
            dropItem(new OBJ_Heart(gp));
        }

    }
}
