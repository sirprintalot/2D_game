package objects;

import entity.*;
import main.*;

public class OBJ_Fireball extends Projectile {

    GamePanel gp;
    public OBJ_Fireball(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "fireball";
        speed = 6;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        isAlive = false;
        getImage();
        
    }

    public void getImage(){

        up1 = setup("/projectile/fireball_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/projectile/fireball_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/projectile/fireball_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/projectile/fireball_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/projectile/fireball_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/projectile/fireball_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/projectile/fireball_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/projectile/fireball_right_2", gp.tileSize, gp.tileSize);
    }

    public boolean haveResources(Entity user){

        boolean haveResource =  false;
        if(user.mana >= useCost){
            haveResource = true;
        }

        return haveResource;
    }

    public void substrackResource(Entity user){
        user.mana -= useCost;
    }


}