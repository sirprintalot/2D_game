package tile_interactive;

import entity.*;
import main.*;
import objects.*;

import java.awt.*;
import java.util.*;

public class IT_DestructibleWall extends InteractiveTile{
    GamePanel gp;

    public IT_DestructibleWall(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.worldX  = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = setup("/interactive_tiles/destructiblewall", gp.tileSize, gp.tileSize);
        destructible = true;

        // set the wall life, so it takes x number of hits to disappear
        life = 1;

    }
    public boolean correctItem(Entity entity){

        boolean correctItem = false;
        if(entity.currentWeapon.type == typePickAxe){
            correctItem = true;
        }

        return correctItem;
    }

    public void playSoundEffect(){
        gp.playSoundEffect(16);
    }

    public InteractiveTile getDestroyedForm(){

        InteractiveTile inTile = new IT_Trunk(gp, worldX/gp.tileSize, worldY/gp.tileSize);
        return inTile;
    }


    public Color getParticleColor(){

        return new Color(65, 65 ,65);

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

            dropItem(new OBJ_BronzeCoin(gp));
        }

    }

}
