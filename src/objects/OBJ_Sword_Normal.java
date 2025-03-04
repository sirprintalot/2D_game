package objects;

import entity.*;
import main.*;

public class OBJ_Sword_Normal extends Entity {
    
    public OBJ_Sword_Normal(GamePanel gp) {
        super(gp);

        type = typeSword;
        name = "Normal Sword";
        down1 = setup("/objects/sword_normal", gp.tileSize, gp.tileSize);
        attackValue = 1;
        knockBackPower = 2;
        attackArea.width = 36;
        attackArea.height = 36;
        itemDescription ="[" + name + "] /nA very old /nsword..";
        price = 99;

    }
}
