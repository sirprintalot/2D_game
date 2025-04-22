package monster;

import entity.*;
import main.*;
import objects.*;

import java.util.*;

public class MON_SkeletonLord extends Entity {
    GamePanel gp;
    public final static String monName = "Skeleton Lord";

    public MON_SkeletonLord(GamePanel gp) {

        super(gp);
        this.gp = gp;

        type = typeMonster;
        name = monName;
        defaultSpeed = 2;
        speed = defaultSpeed;
        maxLife = 50;
        life = maxLife;
        knockBackPower = 7;

        attack = 10;
        defense = 4;
        exp = 50;

        //attack sprites duration
        motion1_duration = 25;
        motion2_duration = 50;

        //solid area

        int size = gp.tileSize * 5;

        solidArea.x = 48;
        solidArea.y = 48;
        solidArea.width = size - 48 * 2;
        solidArea.height = size - 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 170;
        attackArea.height = 170;

        getImage();
        getAttackImage();

    }

    public void getImage() {

        int scale = 5;

        up1 = setup("/monster/skeletonlord_attack_up_1", gp.tileSize * scale, gp.tileSize * scale);
        up2 = setup("/monster/skeletonlord_attack_up_2", gp.tileSize * scale, gp.tileSize * scale);

        down1 = setup("/monster/skeletonlord_attack_down_1", gp.tileSize * scale, gp.tileSize * scale);
        down2 = setup("/monster/skeletonlord_attack_down_2", gp.tileSize * scale, gp.tileSize * scale);

        left1 = setup("/monster/skeletonlord_attack_left_1", gp.tileSize * scale, gp.tileSize * scale);
        left2 = setup("/monster/skeletonlord_attack_left_2", gp.tileSize * scale, gp.tileSize * scale);

        right1 = setup("/monster/skeletonlord_attack_right_1", gp.tileSize * scale, gp.tileSize * scale);
        right2 = setup("/monster/skeletonlord_attack_right_2", gp.tileSize * scale, gp.tileSize * scale);

    }

    public void getAttackImage() {
        int scale = 5;
        attackUp1 = setup("/monster/skeletonlord_attack_up_1", gp.tileSize * scale, gp.tileSize * scale * 2);
        attackUp2 = setup("/monster/skeletonlord_attack_up_2", gp.tileSize * scale, gp.tileSize * scale * 2);

        attackDown1 = setup("/monster/skeletonlord_attack_down_1", gp.tileSize * scale, gp.tileSize * scale * 2);
        attackDown2 = setup("/monster/skeletonlord_attack_down_2", gp.tileSize * scale, gp.tileSize * scale * 2);

        attackLeft1 = setup("/monster/skeletonlord_attack_left_1", gp.tileSize * scale * 2, gp.tileSize * scale);
        attackLeft2 = setup("/monster/skeletonlord_attack_left_2", gp.tileSize * scale * 2, gp.tileSize * scale);

        attackRight1 = setup("/monster/skeletonlord_attack_right_1", gp.tileSize * scale * 2, gp.tileSize * scale);
        attackRight2 = setup("/monster/skeletonlord_attack_right_2", gp.tileSize * scale * 2, gp.tileSize * scale);
    }

    // case 3 NPC getting aggro at certain distance
    public void update() {
        super.update();
    }

    public void setAction() {

        if (onPath) {


        } else {
            getRandomDirection(100);
        }
        if (!attacking) {
            checkAttackOrNot(60, gp.tileSize * 10, gp.tileSize * 5);
        }
    }

    public void damageReaction() {

        actionLockCounter = 0;
        direction = getOppositeDirection(gp.player.direction);

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
