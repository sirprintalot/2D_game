package objects;

import entity.*;
import main.*;

public class OBJ_Lantern extends Entity {

    public OBJ_Lantern(GamePanel gp) {
        super(gp);

        type = typeLight;
        name = "Lantern";
        down1 = setup("/objects/lantern", gp.tileSize, gp.tileSize) ;
        itemDescription = "["+ name + "]/nA small oil /nlantern...";
        price = 200;
        lightRadius = 110;
    }
}
