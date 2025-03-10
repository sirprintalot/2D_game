package objects;

import entity.*;
import main.*;


public class OBJ_Boots extends Entity {

    GamePanel gp;
    public int speedBoosterTimer = 0;
    public int duration = 5000;

    public OBJ_Boots(GamePanel gp) {

        super(gp);
        this.gp = gp;
        value = 5;
        type = typeUsable;
        stackable = true;
        name = "light boots";
        down1 = setup("/objects/boots", gp.tileSize, gp.tileSize);
        itemDescription ="["+ name +"] /nA pair of boots/nSpeed increased!";
        price = 10;
    }
    public boolean useItem(Entity entity){

        gp.gameState = gp.dialogueState;
        gp.playSoundEffect(6);
        gp.ui.currentDialogue = "You wore the  " + name + "!!!" + "/n"+
                "Your speed has increased!";
        
        if(entity.type == typePlayer)  {

            gp.player.speedBoosted = true;
            gp.player.speed += value;
            System.out.println("Active");
            
        }
        
        return true;
    }

}
