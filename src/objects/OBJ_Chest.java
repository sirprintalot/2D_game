package objects;

import entity.*;
import main.*;


public class OBJ_Chest extends Entity {

    GamePanel gp;
    Entity loot;
    boolean opened = false;

    public OBJ_Chest(GamePanel gp, Entity loot){

        super(gp);
        this.gp  = gp;
        this.loot = loot;
        
        name = "chest";
        type = typeObstacle;

        image = setup("/objects/chest", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/chest_opened", gp.tileSize, gp.tileSize);
        down1 =  image;
//        price = 100;
        collision = true;
        solidArea.x = 4;
        solidArea.y =16;
        solidArea.width = 40;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }

    public void interact()  {
        gp.gameState = gp.dialogueState;

        if(!opened){
            gp.playSoundEffect(3);

            StringBuilder sb = new StringBuilder();
            sb.append("You opened the chest /nand found a ").append(loot.name).append("!!");

            // if inventory is fulll
            if(!gp.player.canReceiveItem(loot)){
                sb.append("/nInventory full!").append("/nCan't carry anymore");
            }
            else{
                sb.append("/nYou got a ").append(loot.name).append("!!");
//                gp.player.inventory.add(loot);
                down1 = image2;
                opened = true;
            }
            gp.ui.currentDialogue = sb.toString();
        }
        else{

            gp.ui.currentDialogue = "The chest is empty...";
            down1 = image;

        }
    }
    
}
