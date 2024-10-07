package objects;

import entity.*;
import main.*;


public class OBJ_Boots extends Entity {

    public OBJ_Boots(GamePanel gp) {

        super(gp);
        name = "boots";
        down1 = setup("/objects/key", gp.tileSize, gp.tileSize);


    }
}
