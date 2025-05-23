package objects;

import entity.*;
import main.*;

import java.awt.*;

public class OBJ_Fireball extends Projectile {

    GamePanel gp;
    public static final String objName = "fireball";

    public OBJ_Fireball(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = objName;
        speed = 6;
        maxLife = 80;
        life = maxLife;
        attack = 1;
        knockBackPower = 1;
        useCost = 1;
        isAlive = false;

        getImage();

    }

    public void getImage() {

        up1 = setup("/projectile/fireball_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/projectile/fireball_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/projectile/fireball_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/projectile/fireball_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/projectile/fireball_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/projectile/fireball_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/projectile/fireball_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/projectile/fireball_right_2", gp.tileSize, gp.tileSize);
    }

    public boolean haveResources(Entity user) {

        boolean haveResource = false;
        if (user.mana >= useCost) {
            haveResource = true;
        }

        return haveResource;
    }


    public void substrackResource(Entity user) {
        user.mana -= useCost;

    }

    //particle
    public Color getParticleColor() {

        return new Color(250, 150, 100);

    }

    public int getParticleSize() {
        int size = 8;
        return size;
    }

    public int getParticleSpeed() {

        int speed = 1;
        return speed;
    }

    public int getParticleMaxLife() {

        int maxLife = 25;
        return maxLife;
    }


}
