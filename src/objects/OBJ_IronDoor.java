package objects;

import entity.*;
import main.*;

public class OBJ_IronDoor extends Entity {
    GamePanel gp;
    public static final String objName = "iron door";
    public OBJ_IronDoor(GamePanel gp) {

        super(gp);
        this.gp = gp;

        type = typeObstacle;
        name = objName;
        down1 = setup("/objects/door_iron", gp.tileSize, gp.tileSize);
        collision =  true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDialogue();
    }

    public void setDialogue(){
        dialogues[0][0] =  "Won't open...";
    }

    public void interact(){

        startDialogue(this, 0);
        gp.playSoundEffect(5);
    }
}
