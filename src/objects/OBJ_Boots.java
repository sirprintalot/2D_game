package objects;

import entity.*;
import main.*;


public class OBJ_Boots extends Entity {

    public OBJ_Boots(GamePanel gp) {

        super(gp);
        name = "boots";
        down1 = setup("/objects/boots", gp.tileSize, gp.tileSize);
        itemDescription ="["+ name +"] /nA pair of boots/nSpeed increased!";
        price = 10;

    }
}
