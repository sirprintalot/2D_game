package objects;

import entity.*;
import main.*;

public class OBJ_shield_Wood extends Entity {

    public OBJ_shield_Wood(GamePanel gp) {
        super(gp);
        name = "Wooden Shield";
        down1  = setup("/objects/shield_wood", gp.tileSize, gp.tileSize);
        defenseValue = 1;
    }
}