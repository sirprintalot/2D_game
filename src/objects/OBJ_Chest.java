package objects;

import entity.*;
import main.*;


public class OBJ_Chest extends Entity {

    GamePanel gp;


    public OBJ_Chest(GamePanel gp){

        super(gp);
        this.gp  = gp;

        name = "chest";
        type = typeObstacle;

        image = setup("/objects/chest", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/chest_opened", gp.tileSize, gp.tileSize);
        down1 =  image;
        collision = true;
        solidArea.x = 4;
        solidArea.y =16;
        solidArea.width = 40;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }

    public void setLoot(Entity loot){
        this.loot = loot;
        setDialogue();
    }

    public void setDialogue(){
        dialogues[0][0] = "You opened the chest " +
                "/n and found a " + (loot.name) + "!! " +
                "/n But can't carry anymore..";

        dialogues[1][0] = "You opened the chest " +
                "/n and found a " + (loot.name) + "!!";

        dialogues[2][0] = "The chest is empty!!";
    }

    public void interact()  {

        if(!opened){
            gp.playSoundEffect(3);

            // if inventory is full
            if(!gp.player.canReceiveItem(loot)){
                    startDialogue(this, 0);
            }
            else{
                startDialogue(this, 1);
//                gp.player.inventory.add(loot);
                down1 = image2;
                opened = true;
            }
        }
        else{
            startDialogue(this, 2);
            down1 = image;
        }
    }
    
}
