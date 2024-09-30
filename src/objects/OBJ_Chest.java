package objects;

import entity.*;
import main.*;


public class OBJ_Chest extends Entity {

    public OBJ_Chest(GamePanel gp){

        super(gp);
        name = "chest";
        down1 = setup("/objects/chest");
    }
}
