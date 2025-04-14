package objects;

import entity.*;
import main.*;

public class OBJ_Lantern extends Entity {

    public static final String objName = "Lantern";

    public OBJ_Lantern(GamePanel gp) {
        super(gp);

        type = typeLight;
        name = objName;
        down1 = setup("/objects/lantern", gp.tileSize, gp.tileSize) ;
        itemDescription = "["+ name + "]/nA small oil /nlantern...";
        price = 200;
        lightRadius = 350;
    }
}
