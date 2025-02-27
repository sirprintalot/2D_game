package objects;

import entity.*;
import main.*;

public class OBJ_BronzeCoin extends Entity {

    GamePanel gp;


    public OBJ_BronzeCoin(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = typePickupOnly;
        name = "bronze coin";
        value = 1;
        down1 = setup("/objects/coin_bronze", gp.tileSize, gp.tileSize);

    }

    public boolean useItem(Entity entity){
        gp.playSoundEffect(1);
        gp.ui.addMessage("+ " + value + " Coin");
        gp.player.coin += value;

        return true;
    }
}
