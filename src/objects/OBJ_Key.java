package objects;

import entity.*;
import main.*;


public class OBJ_Key extends Entity {


    public OBJ_Key(GamePanel gp){

        super(gp);
        name = "key";
        down1 = setup("/objects/key", gp.tileSize, gp.tileSize);

        itemDescription ="[" + name + "] /n A golden key";


    }

    
}
