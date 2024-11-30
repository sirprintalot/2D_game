package objects;

import entity.*;
import main.*;

public class OBJ_manaCrystal extends Entity {

    GamePanel gp;
    public OBJ_manaCrystal(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = "mana crystal";
        image = setup("/objects/manacrystal_full", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/manacrystal_blank", gp.tileSize, gp.tileSize);
        



    }
}
