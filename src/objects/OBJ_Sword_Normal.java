package objects;

import entity.*;
import main.*;

public class OBJ_Sword_Normal extends Entity {
    
    public OBJ_Sword_Normal(GamePanel gp) {
        super(gp);

        type = typeSword;
        name = "Normal Sword";
        down1 = setup("/objects/sword_normal", gp.tileSize, gp.tileSize);
        attackValue = 2;
        knockBackPower = 10;
        attackArea.width = 36;
        attackArea.height = 36;
        itemDescription ="[" + name + "] /nA very old /nsword..";
        price = 10;
        motion1_duration = 5;
        motion2_duration = 25;

    }
}
