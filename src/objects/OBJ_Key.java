package objects;

import entity.*;
import main.*;


public class OBJ_Key extends Entity {

    GamePanel gp;

    public OBJ_Key(GamePanel gp){

        super(gp);
        this.gp = gp;
        name = "key";
        type = typeUsable;
        stackable = true;

        down1 = setup("/objects/key", gp.tileSize, gp.tileSize);
        itemDescription ="[" + name + "] /n A golden key/n opens a door";

    }

    public boolean useItem(Entity entity){

        gp.gameState = gp.dialogueState;
        int objIndex = getDetected(entity, gp.obj, "door");

        if(objIndex != 999){

            gp.ui.currentDialogue = name + " used, Door opened...";
            gp.playSoundEffect(3);

            gp.obj[gp.currentMap][objIndex] = null;

            return true;
        }
        else{
            gp.ui.currentDialogue = "Can't use it here...";
            return false;
        }
    }

    
}
