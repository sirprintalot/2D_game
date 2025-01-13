package objects;

import entity.*;
import main.*;

public class OBJ_shield_Wood extends Entity {

    public OBJ_shield_Wood(GamePanel gp) {
        super(gp);

        type = typeShield;
        name = "Wooden Shield";
        down1  = setup("/objects/shield_wood", gp.tileSize, gp.tileSize);
        defenseValue = 1;
        itemDescription ="[" + name + "] /n A light and/n old shield";
        price = 20;
    }
}
