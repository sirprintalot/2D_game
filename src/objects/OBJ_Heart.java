package objects;

import entity.*;
import main.*;


public class OBJ_Heart extends Entity {

    GamePanel gp;
    
    public OBJ_Heart(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = typePickupOnly;
        name = "heart";
        value = 3;
        down1 = setup("/objects/heart_full", gp.tileSize, gp.tileSize);

        image = setup("/objects/heart_full", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/heart_half", gp.tileSize, gp.tileSize);
        image3 = setup("/objects/heart_blank", gp.tileSize, gp.tileSize);

    }

    public boolean useItem(Entity entity){
        gp.playSoundEffect(2);
        gp.ui.addMessage("+ " + value + " Healed!");
        entity.life += value;

        return true;
    }


}
