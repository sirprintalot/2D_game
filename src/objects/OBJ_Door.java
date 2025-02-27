package objects;

import entity.*;
import main.*;


public class OBJ_Door extends Entity {

    GamePanel gp;

    public OBJ_Door(GamePanel gp) {

        super(gp);
        this.gp = gp;

        type = typeObstacle;
        name = "door";
        down1 = setup("/objects/door", gp.tileSize, gp.tileSize);
        collision =  true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public void interact(){

        gp.gameState = gp.dialogueState;
        gp.playSoundEffect(5);
        gp.ui.currentDialogue = "You need a key";
    }

}
