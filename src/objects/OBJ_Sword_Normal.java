package objects;

import entity.*;
import main.*;

public class OBJ_Sword_Normal extends Entity {
    
    public OBJ_Sword_Normal(GamePanel gp) {
        super(gp);
        name = "Normal Sword";
        down1 = setup("/objects/sword_normal", gp.tileSize, gp.tileSize);
        attackValue = 1;
        itemDescription ="[" + name + "] /nA very old sword..";
    }
}
