package objects;

import entity.*;
import main.*;

public class OBJ_Shield_blue extends Entity {

    public OBJ_Shield_blue(GamePanel gp) {
        super(gp);

        type = typeShield;
        name = "Blue Shield";
        down1  = setup("/objects/shield_blue", gp.tileSize, gp.tileSize);
        defenseValue = 3;
        itemDescription ="[" + name + "] /nA brand new/nblue shlied..";
    }
}
