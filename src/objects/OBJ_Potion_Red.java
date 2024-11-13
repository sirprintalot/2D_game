package objects;

import entity.*;
import main.*;

public class OBJ_Potion_Red extends Entity {

    GamePanel gp;
    int healingFactor = 5;

    public OBJ_Potion_Red(GamePanel gp) {
        

        super(gp);
        this.gp = gp;

        type = typeUsable;
        name = "Red potion";
        down1 = setup("/objects/potion_red", gp.tileSize, gp.tileSize);
        itemDescription ="["+ name +"] /nA healing potion!! /n+" + healingFactor + "Heal!!!";
    }

    public void useItem(Entity entity){

        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You Drank the " + name + "!!!" + "/n"+
                "Your life has been restored!";

        entity.life += healingFactor;
        if(gp.player.life > gp.player.maxLife){
            gp.player.life = gp.player.maxLife;
        }
        gp.playSoundEffect(6);

    }




}
