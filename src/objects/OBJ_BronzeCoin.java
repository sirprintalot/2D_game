package objects;

import entity.*;
import main.*;

import java.awt.*;

public class OBJ_BronzeCoin extends Entity {

    GamePanel gp;
    public static final String objName = "bronze coin";


    public OBJ_BronzeCoin(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = typePickupOnly;
        name = objName;
        value = 1;
        down1 = setup("/objects/coin_bronze", gp.tileSize, gp.tileSize);

    }

    public boolean useItem(Entity entity){
        gp.playSoundEffect(1);
        gp.ui.addMessage("+ " + value + " Coin", Color.orange);
        gp.player.coin += value;

        return true;
    }
}
