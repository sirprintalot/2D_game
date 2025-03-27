package objects;

import entity.*;
import main.*;


public class OBJ_Key extends Entity {

    GamePanel gp;
    public static final String objName = "key";

    public OBJ_Key(GamePanel gp){

        super(gp);
        this.gp = gp;
        name = objName;
        type = typeUsable;
        stackable = true;

        down1 = setup("/objects/key", gp.tileSize, gp.tileSize);
        itemDescription ="[" + name + "] /n A golden key/n opens a door";
        setDialogue();

    }

    public void setDialogue(){
        dialogues[0][0] = name + " used, Door opened...";
        
        dialogues[1][0] =  "Can't use it here...";
    }

    public boolean useItem(Entity entity){
        
        int objIndex = getDetected(entity, gp.obj, "door");

        if(objIndex != 999){

            startDialogue(this, 0);
            gp.playSoundEffect(3);
            gp.obj[gp.currentMap][objIndex] = null;

            return true;
        }
        else{
            startDialogue(this, 1);
            return false;
        }
    }

    
}
