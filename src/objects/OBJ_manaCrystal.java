package objects;

import entity.*;
import main.*;

import java.awt.*;

public class OBJ_manaCrystal extends Entity {

    GamePanel gp;
    public OBJ_manaCrystal(GamePanel gp) {
        super(gp);
        this.gp = gp;

        name = "mana crystal";
        stackable = true;
        type = typePickupOnly;
        value = 1;

        down1 = setup("/objects/manacrystal_full", gp.tileSize, gp.tileSize);
        
        image = setup("/objects/manacrystal_full", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/manacrystal_blank", gp.tileSize, gp.tileSize);

    }

    public boolean useItem(Entity entity){
        gp.playSoundEffect(2);
        gp.ui.addMessage("+ " + value + " mana!!", Color.BLUE);
        entity.mana += value;

        return true;
    }
}
