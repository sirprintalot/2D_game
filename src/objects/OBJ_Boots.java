package objects;

import entity.*;
import main.*;


public class OBJ_Boots extends Entity {

    GamePanel gp;

    public OBJ_Boots(GamePanel gp) {

        super(gp);
        this.gp = gp;
        value = 2;
        type = typeUsable;
        name = "light boots";
        down1 = setup("/objects/boots", gp.tileSize, gp.tileSize);
        itemDescription ="["+ name +"] /nA pair of boots/nSpeed increased!";
        price = 10;
    }
    public boolean useItem(Entity entity){

        //TODO create counter for the speed effect to disappear
        gp.gameState = gp.dialogueState;
        gp.playSoundEffect(6);
        gp.ui.currentDialogue = "You wore the  " + name + "!!!" + "/n"+
                "Your speed has increased!";

        entity.speed += value;

        return true;
    }
}
