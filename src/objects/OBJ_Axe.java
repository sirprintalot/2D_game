package objects;

import entity.*;
import main.*;

public class OBJ_Axe extends Entity {
    
    public OBJ_Axe(GamePanel gp) {
        super(gp);

        name = "Axe";
        down1 = setup("/objects/axe", gp.tileSize, gp.tileSize);

        type = typeAxe;
        attackValue = 4;
        knockBackPower = 20;
        attackArea.width = 28;
        attackArea.height = 28;
        itemDescription = "[" +name+"] /nA small axe!!/nAttack +" + attackValue;
        price = 35;
        motion1_duration = 15;
        motion2_duration = 35;

    }
}
