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
        setDialogue();
    }

    public void setDialogue(){
        dialogues[0][0] =  "You need a key" +
                "/n to open this door..";
    }

    public void interact(){

        startDialogue(this, 0);
        gp.playSoundEffect(5);
    }

}
