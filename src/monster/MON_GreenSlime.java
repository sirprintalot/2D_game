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
        speed = 5;
        maxLife = 5;
        life = maxLife;

        attack = 4;
        defense = 0;
        exp = 2;

        projectile = new OBJ_Rock(gp);

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
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

        int i = new Random().nextInt(100) + 1;
         if(i > 99 && !projectile.isAlive && shotAvailableCounter == 30){
             projectile.set(worldX, worldY, direction, true, this);
             gp.projectileList.add(projectile);
             shotAvailableCounter = 0;
         }
    }

    public void damageReaction(){

        actionLockCounter = 0;
        direction = gp.player.direction;
        
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
