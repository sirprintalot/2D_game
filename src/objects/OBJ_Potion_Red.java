package objects;

import entity.*;
import main.*;

public class OBJ_Potion_Red extends Entity {

    GamePanel gp;
    public static final String objName = "Red potion";

    public OBJ_Potion_Red(GamePanel gp) {
        

        super(gp);
        this.gp = gp;

        value = 5;
        type = typeUsable;
        stackable = true;
        name = objName;
        down1 = setup("/objects/potion_red", gp.tileSize, gp.tileSize);
        itemDescription =  "["+ name +"] /nA healing potion!! /n+" + value + "Heal!!!";
        price = 13;

        setDialogue();
    }

    public void setDialogue(){
         dialogues[0][0] =  "You Drank the " + name + "!!!" + "/n"+
                 "Your life has been restored!";
    }

    public boolean useItem(Entity entity){

        startDialogue(this, 0);
        gp.playSoundEffect(6);
        entity.life += value;

        return true;
    }
    
}
