package objects;

import entity.*;
import main.*;

public class OBJ_PickAxe extends Entity {
    GamePanel gp;

    public static final String objName = "Pick Axe";

    public OBJ_PickAxe(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = objName;
        type = typePickAxe;
        down1 = setup("/objects/pickaxe", gp.tileSize, gp.tileSize);
        
        attackValue = 4;
        knockBackPower = 2;
        attackArea.width = 28;
        attackArea.height = 28;
        itemDescription = "[" +name+"] /nA pick axe!!" + attackValue;
        price = 35;
        motion1_duration = 10;
        motion2_duration = 20;

    }
}
