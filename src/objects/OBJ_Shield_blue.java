package objects;

import entity.*;
import main.*;

public class OBJ_Shield_blue extends Entity {

    public static final String objName = "Blue Shield";

    public OBJ_Shield_blue(GamePanel gp) {
        super(gp);

        type = typeShield;
        name = objName;
        down1  = setup("/objects/shield_blue", gp.tileSize, gp.tileSize);
        defenseValue = 3;
        itemDescription ="[" + name + "] /nA brand new/nblue shlied..";
        price = 100;
    }
}
