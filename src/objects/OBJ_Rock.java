package objects;

import entity.*;
import main.*;

import java.awt.*;

public class OBJ_Rock extends Projectile {

    GamePanel gp;
    public OBJ_Rock(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "rock";
        speed = 7;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        isAlive = false;
        knockBackPower = 7;
        getImage();

    }

    public void getImage(){

        up1 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        down1 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        left1 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        right1 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
    }

    public boolean haveResources(Entity user){

        boolean haveResource =  false;
        if(user.ammo >= useCost){
            haveResource = true;
        }

        return haveResource;
    }

    public void substrackResource(Entity user){
        user.ammo -= useCost;
    }

    //Particle
    public Color getParticleColor(){

        return new Color(155, 103 ,60);

    }

    public int getParticleSize(){
        int size = 8;
        return size;
    }

    public int getParticleSpeed(){

        int speed = 1;
        return speed;
    }

    public int getParticleMaxLife(){

        int maxLife = 25;
        return maxLife;
    }

}
