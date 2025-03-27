package objects;

import entity.*;
import main.*;

public class OBJ_Tent extends Entity {

    GamePanel gp;
    public static final String objName = "Tent";

    public OBJ_Tent(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = typeUsable;
        name = objName;
        down1 = setup("/objects/tent", gp.tileSize, gp.tileSize);
        itemDescription = "[" + name + "] /nA small tent. /nYou can use /nit to rest";
        price = 400;
        stackable = true;


    }

    public boolean useItem(Entity entity){
        
        gp.gameState = gp.sleepState;
        gp.playSoundEffect(20);
        gp.player.life = gp.player.maxLife;
        gp.player.mana = gp.player.maxMana;
        gp.player.getSleepingImage(down1);
        return true; ///return false if the item dont expire after one use
    }


}

