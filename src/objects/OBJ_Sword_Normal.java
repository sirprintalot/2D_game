package objects;

import entity.*;
import main.*;

public class OBJ_Sword_Normal extends Entity {

    public static final String objName = "Normal Sword";
    public OBJ_Sword_Normal(GamePanel gp) {
        super(gp);

        type = typeSword;
        name = objName;
        down1 = setup("/objects/sword_normal", gp.tileSize, gp.tileSize);
        attackValue = 10;
        knockBackPower = 10;
        attackArea.width = 36;
        attackArea.height = 36;
        itemDescription ="[" + name + "] /nA very old /nsword..";
        price = 10;
        motion1_duration = 5;
        motion2_duration = 25;

    }
}
